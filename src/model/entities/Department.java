package model.entities;

import java.io.Serializable;
import java.util.Objects;

// Classe que representa um departamento
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	// Id do departamento
	private Integer id;

	// Nome do departamento
	private String name;

	// Construtor vazio
	public Department() {
	}

	// Construtor com parâmetros
	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	// Getter para id do departamento
	public Integer getId() {
		return id;
	}

	// Setter para id do departamento
	public void setId(Integer id) {
		this.id = id;
	}

	// Getter para nome do departamento
	public String getName() {
		return name;
	}

	// Setter para nome do departamento
	public void setName(String name) {
		this.name = name;
	}

	// Método hashCode para id do departamento
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Método equals para id do departamento
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(id, other.id);
	}

	// Representação string da classe
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
