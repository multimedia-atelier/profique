package ma.profique.service;

/**
 * Toto je naše servis. Pro hiring potřebujeme jen "POST", čili create, ale pro
 * další nasazení rozšíříme o další REST-friendly metody.
 */
public interface Service<E> {

	E create(E e);

}
