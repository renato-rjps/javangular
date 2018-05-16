package br.com.rsbdev.starter.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class DateUtil {
	
	/**
	 * UTC Zone reference.
	 */
	public static final ZoneId ZONE_UTC = ZoneId.of("UTC");
	
	/**
	 * Local Zone reference.
	 */
	public static final ZoneId ZONE_LOCAL = ZoneId.systemDefault();
	
	// Utility class
	private DateUtil() {} 
	
	
	/**
	 * Convert {@link LocalDate} to {@link Date}.
	 */
	public static Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().toInstant(zoneOffsetNow()));
	}
	
	/**
	 * Convert {@link LocalDateTime} to {@link Date}.
	 */
	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.toInstant(zoneOffsetNow()));
	}
	
	/**
	 * Convert {@link Date} to {@link LocalDate}.
	 */
	public static LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZONE_LOCAL).toLocalDate();
	}
	
	/**
	 * Convert {@link Date} to {@link LocalDateTime}.
	 */
	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZONE_LOCAL);
	}
	
	/**
	 * Convert {@link Optional}&lt;{@link Date}&gt to {@link Optional}&lt;{@link LocalDate}&gt
	 */
	public static Optional<LocalDate> toLocalDate(Optional<Date> date) {
		LocalDate localDate = null;
		if (date.isPresent()) {
			localDate = toLocalDate(date.get());
		}
		return Optional.ofNullable(localDate);
	}
	
	/**
	 * Convert {@link Optional}&lt;{@link Date}&gt to {@link Optional}&lt;{@link LocalDateTime}&gt.
	 */
	public static Optional<LocalDateTime> toLocalDateTime(Optional<Date> date) {
		LocalDateTime localDateTime = null;
		if (date.isPresent()) {
			localDateTime = toLocalDateTime(date.get());
		}
		return Optional.ofNullable(localDateTime);
	}
	
	/**
	 * Convert {@link Date} to {@link YearMonth}.
	 */
	public static YearMonth toYearMonth(Date date) {
		return YearMonth.from(toLocalDate(date));
	}

	/**
	 * Convert {@link YearMonth} to {@link Date},
	 * on the first day of that month.
	 */
	public static Date toDate(YearMonth yearMonth) {
		return toDate(yearMonth.atDay(1));
	}
	
	/**
	 * Create a new date using the UTC timezone.
	 * @return a {@link LocalDateTime}
	 */
	public static LocalDateTime dateTimeNow() {
		return LocalDateTime.now(Clock.systemUTC());
	}
	
	/**
	 * Create a new date using the UTC timezone.
	 * 
	 * @return a {@link LocalDate}
	 */
	public static LocalDate dateNow() {
		return LocalDate.now(Clock.systemUTC());
	}
	
	/**
	 * Create a new date using the UTC timezone.
	 * 
	 * @return a {@link LocalDate}
	 */
	public static Instant instantNow() {
		return Instant.now(Clock.systemUTC());
	}
	
	
	/**
	 * Return the current time offset, relative to UTC.
	 */
	public static ZoneOffset zoneOffsetNow() {
		return OffsetTime.now().getOffset();
	}
	
	public static final Locale LOCALE_PT_BR = new Locale("pt-BR");
	public static final DateTimeFormatter FORMAT_LOCAL_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy", LOCALE_PT_BR);
	public static final DateTimeFormatter FORMAT_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", LOCALE_PT_BR);
	public static final DateTimeFormatter FORMAT_YEAR_MONTH = DateTimeFormatter.ofPattern("MMMM/yyyy", LOCALE_PT_BR);
	
	/**
	 * Converte para string amigável.
	 */
	public static String toString(LocalDate date) {
		return FORMAT_LOCAL_DATE.format(date);
	}
	
	/**
	 * Converte para string amigável.
	 */
	public static String toString(LocalDateTime dateTime) {
		return FORMAT_LOCAL_DATE_TIME.format(dateTime);
	}
	
	/**
	 * Converte para string amigável.
	 */
	public static String toString(YearMonth yearMonth) {
		return FORMAT_YEAR_MONTH.format(yearMonth);
	}

}
