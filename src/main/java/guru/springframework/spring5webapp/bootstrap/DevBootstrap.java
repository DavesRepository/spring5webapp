package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private PublisherRepository publisherRepository;
  private AuthorRepository authorRepository;
  private BookRepository bookRepository;

  public DevBootstrap(PublisherRepository publisherRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
    this.publisherRepository = publisherRepository;
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
  }

  private void initData(){

    final Publisher harper = new Publisher("Harper Collins");
    harper.setStreet("12th street");
    harper.setCity("LA");
    harper.setHouseNumber("12");
    harper.setZipcode("XXXX");
    publisherRepository.save(harper);

    //Eric
    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driven Design", "1234", harper);
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    authorRepository.save(eric);
    bookRepository.save(ddd);

    final Publisher worx = new Publisher("Worx");
    worx.setStreet("13th avenue");
    worx.setCity("LA");
    worx.setHouseNumber("32");
    worx.setZipcode("XXXX");
    publisherRepository.save(worx);

    //Rod
    Author rod = new Author("Rod", "Johnson");
    Book notEJB = new Book("J2EE DEvelopment without EJB", "23444", worx);
    rod.getBooks().add(notEJB);

    authorRepository.save(rod);
    bookRepository.save(notEJB);
  }
}
