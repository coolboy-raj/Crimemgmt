import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CriminalRecord {
    private int id;
    private String name;
    private String crime;

    public CriminalRecord(int id, String name, String crime) {
        this.id = id;
        this.name = name;
        this.crime = crime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCrime() {
        return crime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Crime: " + crime;
    }
}

class Admin {
    private List<CriminalRecord> records;

    public Admin(List<CriminalRecord> records) {
        this.records = records;
    }

    public void addRecord(int id, String name, String crime) {
        CriminalRecord record = new CriminalRecord(id, name, crime);
        records.add(record);
        System.out.println("Record added successfully.");
    }

    public void deleteRecord(int id) {
        for (CriminalRecord record : records) {
            if (record.getId() == id) {
                records.remove(record);
                System.out.println("Record deleted successfully.");
                return;
            }
        }
        System.out.println("Record not found.");
    }

    public void editRecord(int id, String newName, String newCrime) {
        for (CriminalRecord record : records) {
            if (record.getId() == id) {
                record.setName(newName);
                record.setCrime(newCrime);
                System.out.println("Record updated successfully.");
                return;
            }
        }
        System.out.println("Record not found.");
    }

    public void viewRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (CriminalRecord record : records) {
                System.out.println(record.toString());
            }
        }
    }
}

class RecordManager {
    private List<CriminalRecord> records;

    public RecordManager(List<CriminalRecord> records) {
        this.records = records;
    }

    public void addRecord(int id, String name, String crime) {
        CriminalRecord record = new CriminalRecord(id, name, crime);
        records.add(record);
        System.out.println("Record added successfully.");
    }

    public void viewRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (CriminalRecord record : records) {
                System.out.println(record.toString());
            }
        }
    }
}

class Constable {
    private List<CriminalRecord> records;

    public Constable(List<CriminalRecord> records) {
        this.records = records;
    }

    public void viewRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (CriminalRecord record : records) {
                System.out.println(record.toString());
            }
        }
    }
}

public class CrimeRecordManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<CriminalRecord> sharedRecords = new ArrayList<>();
        Admin admin = new Admin(sharedRecords);
        RecordManager recordManager = new RecordManager(sharedRecords);
        Constable constable = new Constable(sharedRecords);

        while (true) {
            System.out.println("\nSelect login type: ");
            System.out.println("1. Admin");
            System.out.println("2. Record Manager");
            System.out.println("3. Constable");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();
                    if (password.equals("admin123")) {
                        while (true) {
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. Add Record");
                            System.out.println("2. Delete Record");
                            System.out.println("3. Edit Record");
                            System.out.println("4. View Records");
                            System.out.println("5. Logout");

                            int adminChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (adminChoice) {
                                case 1:
                                    System.out.print("Enter criminal ID: ");
                                    int id = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter criminal name: ");
                                    String name = scanner.nextLine();
                                    System.out.print("Enter crime description: ");
                                    String crime = scanner.nextLine();
                                    admin.addRecord(id, name, crime);
                                    break;
                                case 2:
                                    System.out.print("Enter criminal ID to delete: ");
                                    int deleteId = scanner.nextInt();
                                    scanner.nextLine();
                                    admin.deleteRecord(deleteId);
                                    break;
                                case 3:
                                    System.out.print("Enter criminal ID to edit: ");
                                    int editId = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter new name: ");
                                    String newName = scanner.nextLine();
                                    System.out.print("Enter new crime: ");
                                    String newCrime = scanner.nextLine();
                                    admin.editRecord(editId, newName, newCrime);
                                    break;
                                case 4:
                                    admin.viewRecords();
                                    break;
                                case 5:
                                    System.out.println("Logged out.\n");
                                    break;
                                default:
                                    System.out.println("Invalid choice.");
                            }

                            if (adminChoice == 5) break;
                        }
                    } else {
                        System.out.println("Invalid password.");
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println("\nRecord Manager Menu:");
                        System.out.println("1. Add Record");
                        System.out.println("2. View Records");
                        System.out.println("3. Logout");

                        int managerChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (managerChoice) {
                            case 1:
                                System.out.print("Enter criminal ID: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter criminal name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter crime description: ");
                                String crime = scanner.nextLine();
                                recordManager.addRecord(id, name, crime);
                                break;
                            case 2:
                                recordManager.viewRecords();
                                break;
                            case 3:
                                System.out.println("Logged out.\n");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }

                        if (managerChoice == 3) break;
                    }
                    break;
                case 3:
                    System.out.println("\nConstable View:");
                    constable.viewRecords();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
