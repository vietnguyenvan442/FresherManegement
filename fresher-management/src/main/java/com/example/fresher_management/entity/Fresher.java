package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fresher")
public class Fresher extends User implements Serializable {
	private final static int MAX_RESULT = 3;

	@JoinColumn(name = "language_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Language language;

	@JsonBackReference(value = "fresher-record")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fresher", cascade = CascadeType.ALL)
	private List<Record> listRecord;

	@JsonBackReference(value = "fresher-result")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fresher", cascade = CascadeType.ALL)
	private List<Result> listResult;

	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Float avg;

	@JsonProperty("avg")
	protected Float getAvg(){
		if (listResult != null && listResult.size() == MAX_RESULT){
			return calculaterAvg();
		}
		return null;
	}

	private float calculaterAvg(){
		Float res = 0f;
		for (Result result: listResult){
			res += result.getPoint();
		}
		return (float) (Math.ceil(res/3*100)/100);
	}
}
