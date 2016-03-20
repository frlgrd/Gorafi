package com.frlgrd.rssstream.core.cache.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;

public class JodaPeriodTypeSerializer extends Serializer<PeriodType> {

	public JodaPeriodTypeSerializer() {
		setImmutable(true);
	}

	@Override
	public void write(final Kryo kryo, final Output output, final PeriodType obj) {
		output.writeInt(obj.size());

		for (int i = 0, size = obj.size(); i < size; i++) {
			DurationFieldType fieldType = obj.getFieldType(i);
			output.writeString(fieldType.getName());
		}
	}

	@Override
	public PeriodType read(final Kryo kryo, final Input input, final Class<PeriodType> type) {
		final int fieldCount = input.readInt();

		DurationFieldType[] durationFieldTypes = new DurationFieldType[fieldCount];
		for (int i = 0; i < fieldCount; i++) {
			String fieldTypeName = input.readString();
			durationFieldTypes[i] = resolveDurationFieldType(fieldTypeName);
		}

		return PeriodType.forFields(durationFieldTypes);
	}

	private DurationFieldType resolveDurationFieldType(String fieldTypeName) {
		if ("eras".equals(fieldTypeName)) {
			return DurationFieldType.eras();
		} else if ("centuries".equals(fieldTypeName)) {
			return DurationFieldType.centuries();
		} else if ("weekyears".equals(fieldTypeName)) {
			return DurationFieldType.weekyears();
		} else if ("years".equals(fieldTypeName)) {
			return DurationFieldType.years();
		} else if ("months".equals(fieldTypeName)) {
			return DurationFieldType.months();
		} else if ("weeks".equals(fieldTypeName)) {
			return DurationFieldType.weeks();
		} else if ("days".equals(fieldTypeName)) {
			return DurationFieldType.days();
		} else if ("halfdays".equals(fieldTypeName)) {
			return DurationFieldType.halfdays();
		} else if ("hours".equals(fieldTypeName)) {
			return DurationFieldType.hours();
		} else if ("minutes".equals(fieldTypeName)) {
			return DurationFieldType.minutes();
		} else if ("seconds".equals(fieldTypeName)) {
			return DurationFieldType.seconds();
		}
		return DurationFieldType.millis();
	}

}