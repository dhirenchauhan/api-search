package com.api.apisearch.util;

import com.api.apisearch.exception.BadRequestException;
import com.api.apisearch.exception.ResourceNotFoundException;

import static java.util.Objects.isNull;

public final class ConditionCheck {

	private ConditionCheck() {
	}
	
	public static <T> T checkNotNull(final T reference) {
		return checkNotNull(reference, null);
	}

	public static <T> T checkNotNull(final T reference, final String message) {
		if (isNull(reference)) {
			throw new ResourceNotFoundException(message);
		}

		return reference;
	}

	public static <T> T checkRequestElementNotNull(final T reference) {
		return checkNotNull(reference, null);
	}

	public static <T> T checkRequestElementNotNull(final T reference, final String message) {
		if (isNull(reference)) {
			throw new BadRequestException(message);
		}

		return reference;
	}
}
