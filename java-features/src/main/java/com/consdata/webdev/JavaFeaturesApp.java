package com.consdata.webdev;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class JavaFeaturesApp {

	public static void main(String[] args) {
		Book book1 = Book.builder().title("Bible").build();
		Book book2 = Book.builder().title("Asian Cookbook").build();


		List<Book> list = Collections.singletonList(book1);

		System.out.println("List " + (contains(list).test(book1) ? "contains ".concat(book1.getTitle()): "does not contain ".concat(book1.getTitle())));
		System.out.println("List " + (contains(list).test(book2) ? "contains ".concat(book2.getTitle()): "does not contain ").concat(book2.getTitle()));

		ExceptionThrower exceptionThrower = new ExceptionThrower();

		try {
			exceptionThrower.throwException();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private Predicate<Book> highlightDisabled() {
		return value -> false;
	}

	private static Predicate<Book> contains(final List<Book> values) {
		return values::contains;
	}

}
