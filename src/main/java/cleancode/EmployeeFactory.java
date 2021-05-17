package cleancode;

interface EmployeeFactory {
	Employee makeEmployee(EmployeeRecord r) throws InvalidEmployeeType;
}
