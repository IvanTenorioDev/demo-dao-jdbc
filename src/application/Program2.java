package application;

//Esta classe Program2 é responsável por testar as operações CRUD (Create, Read, Update e Delete)
//realizadas pelo objeto departmentDao, que é criado pela classe DaoFactory.

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);
	
	// Cria um objeto departmentDao, que é responsável por realizar as operações CRUD em uma tabela de departamentos.
	DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

	System.out.println("=== TEST 1: findById =======");
	// Testa a operação de leitura (Read) do departamento com id 1.
	Department dep = departmentDao.findById(1);
	System.out.println(dep);
	
	System.out.println("\n=== TEST 2: findAll =======");
	// Testa a operação de leitura (Read) de todos os departamentos.
	List<Department> list = departmentDao.findAll();
	for (Department d : list) {
		System.out.println(d);
	}

	System.out.println("\n=== TEST 3: insert =======");
	// Testa a operação de criação (Create) de um novo departamento.
	Department newDepartment = new Department(null, "Music");
	departmentDao.insert(newDepartment);
	System.out.println("Inserted! New id: " + newDepartment.getId());

	System.out.println("\n=== TEST 4: update =======");
	// Testa a operação de atualização (Update) de um departamento existente.
	Department dep2 = departmentDao.findById(1);
	dep2.setName("Food");
	departmentDao.update(dep2);
	System.out.println("Update completed");
	
	System.out.println("\n=== TEST 5: delete =======");
	// Testa a operação de exclusão (Delete) de um departamento existente.
	System.out.print("Enter id for delete test: ");
	int id = sc.nextInt();
	departmentDao.deleteById(id);
	System.out.println("Delete completed");

	sc.close();
}
}
