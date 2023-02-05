package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {

	// ID da classe para uso do serializable
	private static final long serialVersionUID = 1L;

	// Atributos da classe Seller
	private Integer id;
	private String name;
	private String email;
	private Date birthDate;
	private Double baseSalary;

	/*
	 * A composição está presente na classe Seller, na propriedade
	 * "Department department". Isso significa que a classe Seller tem uma instância
	 * da classe Department. Isso ocorre porque existe uma relação entre um vendedor
	 * e um departamento, onde um vendedor pertence a um departamento.
	 * 
	 * A composição é importante porque permite que a classe Seller tenha acesso aos
	 * atributos e métodos da classe Department, e também permite que sejam feitas
	 * relações entre objetos de classes diferentes, tornando a modelagem do sistema
	 * mais organizada e coerente. Além disso, a composição fornece flexibilidade
	 * para modificar a classe Department sem afetar a classe Seller, o que facilita
	 * a manutenção do código.
	 */
	private Department department;

	// Construtor padrão
	public Seller() {
	}

	// Construtor com todos os atributos
	public Seller(Integer id, String name, String email, Date birthDate, Double baseSalary, Department department) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	// Métodos getters e setters para todos os atributos
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	// Implementação do método hashCode
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Implementação do método equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}

	// Implementação do método toString
	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate + ", baseSalary="
				+ baseSalary + ", department=" + department + "]";
	}
}
