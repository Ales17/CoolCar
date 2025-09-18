package cz.ales17.auto.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import cz.ales17.auto.dto.ApiResponseData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Setter
@Getter
@Table(name = "api_call")
public class ApiCall extends AbstractEntity {

    @Type(JsonStringType.class)
    @Column(columnDefinition = "JSON")
    private ApiResponseData responseData;

    @ManyToOne
    private Car vehicle;

}
