package test.app.asist.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.UUID;

public class Employee {
    private final UUID id;
    private final String name;
    private final Integer workingHours;
    private final Integer vacationHours;
    private final Integer sickHours;
    private final Date date;
    public Employee(@JsonProperty("id") UUID id,
                    @JsonProperty("name") String name,
                    @JsonProperty("workingHours") Integer workingHours,
                    @JsonProperty("vacationHours") Integer vacationHours,
                    @JsonProperty("sickHours") Integer sickHours,
                    @JsonProperty("date") Date date
    ) {
        this.id = id;
        this.name = name;
        this.workingHours = workingHours;
        this.vacationHours = vacationHours;
        this.sickHours = sickHours;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public Integer getVacationHours() {
        return vacationHours;
    }

    public Integer getSickHours() {
        return sickHours;
    }

    public Date getDate() {
        return date;
    }
}
