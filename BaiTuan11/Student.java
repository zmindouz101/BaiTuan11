package Database;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Student implements Serializable{
	private int id;
	private String ten;
	private int tuoi;
	private float mark;
	public Student() {
		this.setId(0);
		this.setTen("");
		this.setTuoi(22);
		this.setMark(0);
	}
	
	public Student(int id, String ten, int tuoi, float mark) {
		super();
		this.id = id;
		this.ten = ten;
		this.tuoi = tuoi;
		this.mark = mark;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	@SuppressWarnings("resource")
	public void InputStudent()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("StudentID: ");
		this.id = sc.nextInt();
		System.out.print("Name: ");
		this.ten = sc.next();
		System.out.print("Age: ");
		this.tuoi = sc.nextInt();
		System.out.print("Mark: ");
		this.mark = sc.nextFloat();
	}
	public void OutputStudent()
	{
		System.out.print("ID: "+this.id+"\t"+this.ten+"\t"+this.tuoi+"\t"+this.mark+"\n");
	}
}
