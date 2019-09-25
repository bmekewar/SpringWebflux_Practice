package com.bvm.reactor;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.constraints.NotNull;

import lombok.Cleanup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

//@Getter
//@Setter
//@NoArgsConstructor // : Generates a no-args constructor. Will generate an error message if such a
					// constructor cannot be written due to the existence of final fields.
//@ToString // :Generates an implementation for the toString method inherited by all
			// objects, consisting of printing the values of relevant fields.

//@EqualsAndHashCode // : Generates implementations for the equals and hashCode methods inherited by
					// all objects, based on relevant fields.

//@RequiredArgsConstructor // :Generates a constructor with required arguments. Required arguments are
							// final fields and fields with constraints such as @NonNull.
@Accessors(fluent = true) // : A container for settings for the generation of getters and setters. Whether
							// or not to make fluent methods (named fieldName(), not for example
							// setFieldName).
@Data // : Generates getters for all fields, a useful toString method, and hashCode
		// and equals implementations that check all non-transient fields. Will also
		// generate setters for all non-final fields, as well as a constructor.
		// Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString
		// @EqualsAndHashCode.

@Slf4j
public class User {

	private @NotNull String firstName;
	private @NotNull String lastName;
	private @NotNull int age;
	
	public void test() throws IOException{
		@Cleanup("close") InputStream is = this.getClass().getResourceAsStream("Event.json");
	}
	@Synchronized
	public /* better than: synchronized */ void putValueInCache(String key, Object value) {
	    // whatever here will be thread-safe code
	}
}
