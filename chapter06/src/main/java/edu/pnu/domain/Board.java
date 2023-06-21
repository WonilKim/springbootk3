package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	private String title;

	@Column(updatable = false)
	private String writer;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private Date createDate;

	@Column(insertable = false, updatable = false, columnDefinition = "number default 0")
	private Long cnt;
}
