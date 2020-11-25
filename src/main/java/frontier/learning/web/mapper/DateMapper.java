package frontier.learning.web.mapper;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

@Component
public class DateMapper {

	public OffsetDateTime asOffsetDateTime(Timestamp timestamp) {
		return timestamp != null ? OffsetDateTime.of(timestamp.toLocalDateTime().getYear(),
				timestamp.toLocalDateTime().getMonthValue(), timestamp.toLocalDateTime().getDayOfMonth(),
				timestamp.toLocalDateTime().getHour(), timestamp.toLocalDateTime().getMinute(),
				timestamp.toLocalDateTime().getSecond(), timestamp.toLocalDateTime().getNano(), ZoneOffset.UTC) : null;
	}

	public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
		return offsetDateTime != null
				? Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
				: null;
	}

}
