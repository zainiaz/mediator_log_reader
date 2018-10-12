import logthingy.FileReader;

public class test{
	public static void main(String[] args){
		System.out.println(System.getProperty("user.dir") + " current");
		UI w = new UI(500, 200);
		w.ShowWindow();
	}
}
