package poleChudes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Adapter {
	private LinkedHashMap<String,String> qMap;
	private String tempQuestion;
	private String tempAnswer;
	private List<String> list;
	Adapter(){
		qMap = new LinkedHashMap<String, String>();
		list = new ArrayList<String>();
		list.add("“ак в старину называли сторожа городских ворот");
		list.add("∆елезна€ дорога с канатной т€гой, устраиваема€ на крутых подьемах");
		list.add("јнисова€ настойка или ликер");
		list.add("„то мексиканцы изготовл€ли из волокнистой древесины кактусов?");
		list.add(" акое животное дало название распространенному в ƒревнем –име способу боевого построени€?");
		list.add("÷итрусовое дерево с ароматными цветками");
		list.add("Ќазвание этого растени€ произошло от греческого Ђпорождающий чистотуї");
		list.add("Ёта птица может летать спиной вперед");
		list.add("Ѕедный, неказистый домишко, избенка");
		list.add("¬ метро этого города дл€ того, чтобы играть в переходах, нужно еще получить специальную лицензию");
		qMap.put(list.get(0), "вратарь");
		qMap.put(list.get(1),"фуникулер");
		qMap.put(list.get(2),"абсент");
		qMap.put(list.get(3),"воротник");
		qMap.put(list.get(4),"черепаха");
		qMap.put(list.get(5),"бергамот");
		qMap.put(list.get(6),"баклажан");
		qMap.put(list.get(7),"колибри");
		qMap.put(list.get(8),"хибара");
		qMap.put(list.get(9),"торонто");
	}
	
	public void newQuestion(){
		int num = (int) (Math.random()*qMap.size());
		tempQuestion = list.get(num);
		tempAnswer = qMap.get(tempQuestion);
	}
	
	public String getQuestion(){
		return tempQuestion;
	}
	
	public String getAnswer(){
		return tempAnswer;
	}
	
	public int checkLetter(String s){
		if(tempAnswer.contains(s.toLowerCase().trim())){
			int i = tempAnswer.indexOf(s.toLowerCase().trim());
			String s1,s2;
			s1 = "";
			s2 = "";
			if(i!=0)
				s1 = tempAnswer.substring(0, tempAnswer.indexOf(s.toLowerCase().trim()));
			s2 = tempAnswer.substring(tempAnswer.indexOf(s.toLowerCase().trim())+1, tempAnswer.length());
			tempAnswer = s1 + "/" + s2;
			return i;
		}
		else return -1;
	}
}
