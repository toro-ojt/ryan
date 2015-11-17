import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassStats {

	public static ArrayList list = new ArrayList();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Students stud = new Students();
		Scanner scan = new Scanner(System.in);
		String[] int_com, col = null, assign = null;
		String com, line, ch = "", rate = "";

		while (true) {
			System.out.print("> ");
			com = scan.nextLine();

			int_com = com.split(" ");

			switch (int_com[0]) {
			case "exit":
				System.out.println("exiting.....");
				return;
			case "load":
				if (int_com.length > 2) {
					for (int i = 1; i <= int_com.length - 1; i++) {
						ch = ch + int_com[i] + " ";
					}

				} else {
					ch = int_com[1];
				}

				load_data(ch.trim());
				try {
					line = (String) list.get(1);
					col = line.split(",");
					System.out.println("\tloaded " + col[0].toString() + " section " + col[1].toString());
				} catch (IndexOutOfBoundsException e) {
					break;
				}
				break;
			case "help":
				System.out.println("Accepted Commands");
				System.out.println("\texit");
				System.out.println("\tload [filename]");
				System.out.println("\tstudents");
				System.out.println("\tsearch [partial name]");
				System.out.println("\tassignments");
				System.out.println("\tgrades");
				System.out.println("\tstudent [student name]");
				System.out.println("\tassignment [assignment name]");
				break;
			case "students":
				System.out.println("\tStudents Grade for " + col[0].toString() + ", section " + col[1].toString()
						+ "\n\n\tTotal Possible Points: 100");
				System.out.println(
						"\n\tFirst Name\t\t\tLast Name\t\t\tPoints\t\t\tGrades\n\t----------\t\t\t---------\t\t\t------\t\t\t------");
				for (int x = 2; x <= list.size() - 1; x++) {
					line = (String) list.get(x);
					String[] st = line.split(",");
					setStudentsValue(st, stud);

					double maxgr = Integer.parseInt(col[6].toString());
					double raw_grade = ((stud.getFgrade() / maxgr) * 100);

					rate = getRate(raw_grade);

					
					// load /users/ryan.nacpil/desktop/test.txt
					System.out.println("\t" + stud.getFname() + "  \t\t\t" + stud.getLname() + "  \t\t\t"
							+ String.valueOf((int) raw_grade) + "\t\t\t" + rate);
				}
				break;
			case "assignments":
				line = null;
				line = (String) list.get(0);
				assign = line.split(",");
				System.out.println("\tAssignments for " + col[0].toString() + ", " + col[1].toString());
				System.out.println("\n\tAssignment\t\tPoints\n\t----------\t\t------");
				for (int x = 2; x <= assign.length - 1; x++) {
					System.out.println("\t" + assign[x].toString() + "\t\t\t" + col[x].toString());
				}
				break;
			case "search":
				if (int_com.length > 2) {
					for (int i = 1; i <= int_com.length - 1; i++) {
						ch = ch + int_com[i] + " ";
					}

				} else {
					ch = int_com[1];
				}
				
				System.out.println(
						"\tFirst Name\t\t\tLast Name\t\t\tPoints\t\tGrade\n\t----------\t\t\t---------\t\t\t------\t\t-----");
				
				for (int x = 2; x <= list.size() - 1; x++) {
					line = (String) list.get(x);
					String[] st = line.split(",");
					setStudentsValue(st, stud);

					double maxgr = Integer.parseInt(col[6].toString());
					double raw_grade = ((stud.getFgrade() / maxgr) * 100);

					rate = getRate(raw_grade);
					
					if (stud.getFname().toLowerCase().contains(ch.toLowerCase())
							|| stud.getFname().toLowerCase().equals(ch.toLowerCase())) {
						System.out.println("\t" + stud.getFname() + "\t\t\t" + stud.getLname() + "\t\t\t"
								+ String.valueOf((int) raw_grade) + "\t\t" + rate);
					}

				}

				break;
			case "grades":
				System.out.println("Grade breakdown for " + col[0].toString() + ", section " + col[1].toString());
				break;
			default:
				System.out.println("Invalid Choice, Please Use the command Help to show all the available commands..");
				break;
			}
		}

	}

	public static void load_data(String int_com) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(int_com))) {
			list = (ArrayList<?>) stream.collect(Collectors.toList());
			// ArrayList list = new ArrayList(); */
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("File Not found..");
			;
		}

	}
	
	private static String getRate(double raw_grade){
		String rate = "";
		if (raw_grade >= 90) {
			rate = "A";
		} else if (raw_grade >= 80 && raw_grade < 90) {
			rate = "B";
		} else if (raw_grade >= 70 && raw_grade < 80) {
			rate = "C";
		} else if (raw_grade >= 60 && raw_grade < 70) {
			rate = "D";
		} else if (raw_grade < 60) {
			rate = "F";
		}
		return rate;
	}
	
	private static void setStudentsValue(String[] st, Students stud){
		stud.setFname(st[0].toString());
		stud.setLname(st[1].toString());
		stud.setEssay1(Integer.parseInt(st[2].toString()));
		stud.setTest1(Integer.parseInt(st[3].toString()));
		stud.setEssay2(Integer.parseInt(st[4].toString()));
		stud.setTest2(Integer.parseInt(st[5].toString()));
		stud.setFgrade(Integer.parseInt(st[6].toString()));
	}
	
}
