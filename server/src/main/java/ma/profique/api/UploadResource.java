package ma.profique.api;

import com.google.appengine.tools.cloudstorage.*;
import ma.profique.api.jersey.ResultFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Path("/uploads")
@Produces(MediaType.APPLICATION_JSON)
public class UploadResource extends BaseResource {

	private static final String BUCKET = "file-bucket";

	@POST
	@Consumes(MediaType.WILDCARD)
	public Response register(@Context HttpHeaders headers, InputStream data) throws IOException {

		String origName = "";
		List<String> possible = headers.getRequestHeader("X-Filename");
		if (possible != null && possible.size()>=1) {
			origName = possible.get(0);
		}
		origName = Normalizer.normalize(origName, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

		String fileId = UUID.randomUUID().toString() + origName;
		fileId = fileId.trim().toLowerCase().replaceAll("[^a-z0-9\\.\\-_]+", "_");

		log.info("Saving file : "+fileId+" ("+headers.getMediaType().toString()+") into bucket: "+BUCKET);

		GcsFilename gcsFilename = new GcsFilename(BUCKET, fileId);

		GcsFileOptions o = new GcsFileOptions.Builder().mimeType(headers.getMediaType().toString()).build();
		GcsOutputChannel outputChannel = GcsServiceFactory.createGcsService().createOrReplace(gcsFilename, o);

		copy(data, Channels.newOutputStream(outputChannel));
		outputChannel.close();

		Map<String, String> result = new HashMap<String, String>();
		result.put("fileId", fileId);

		return ResultFactory.resultOkWithCrossOrigin(result);
	}

	@GET
	@Path("/view/{fileId}")
	public Response view(@PathParam("fileId") String fileId) throws IOException {

		// let's contruct cloud file name
		GcsFilename gcsFilename = new GcsFilename(BUCKET, fileId);

		GcsService gcsService = GcsServiceFactory.createGcsService();

		// load metadata
		GcsFileMetadata fileMetadata = gcsService.getMetadata(gcsFilename);

		// open channel
		GcsInputChannel readChannel = gcsService.openReadChannel(gcsFilename, 0);

		// serve!
		return Response.ok(Channels.newInputStream(readChannel), fileMetadata.getOptions().getMimeType()).build();
	}

	private void copy(InputStream input, OutputStream output) throws IOException {
		try {
			byte[] buffer = new byte[100*1024];
			int bytesRead = input.read(buffer);
			if (bytesRead == -1) {
				log.warning("No file data");
			}
			while (bytesRead != -1) {
				output.write(buffer, 0, bytesRead);
				bytesRead = input.read(buffer);
			}
		} finally {
			input.close();
			output.close();
		}
	}

}