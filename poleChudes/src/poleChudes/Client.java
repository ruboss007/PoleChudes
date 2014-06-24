package poleChudes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client {
	private JFrame frame;
	private JTextField textField;
	private Adapter adapter;
	private String question;
	private int answerCount;
	private int number;
	private List<JTextField> list;
	private JTextField attempt;
	private boolean win;
	private int right;
	public Client(){
		right = 0;
		win = false;
		number = 15;
		list = new ArrayList<JTextField>();
		adapter = new Adapter();
		adapter.newQuestion();
		question = adapter.getQuestion();
		answerCount = adapter.getAnswer().length();
		System.out.println(adapter.getAnswer());
		frame = new JFrame("Поле чудес");
		frame.setSize(new Dimension(600,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //выход из приложения по нажатию клавиши ESC
        frame.setLocation(200, 200);								//устанавливаем верхний левый угол
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());
        init();
       	frame.pack();
        frame.setResizable(false);								//запрет на изменение размера
        frame.setVisible(true);							
		
	}
	public static void getClient(){
		new Client();
	}
	
	public void init(){
		 initButtons();
		 initTextFields();
		 initWordPanel();
		 initAlphabet();
	}
	
	private void initAlphabet(){
		 JPanel panel1 = new JPanel();
		 panel1.setBackground(Color.cyan);
		 JPanel panel2 = new JPanel();
		 panel2.setBackground(Color.cyan);
		 String FontName = "Impact"; 
		 int style = 0, size = 24;
		 Font TextFont = new Font(FontName, style, size);
		 
		 ArrayList<JTextField> listA = new ArrayList<JTextField>();
		 ArrayList<Character> listC = new ArrayList<Character>();
		 
		 listC.add('А'); listC.add('Б'); listC.add('В'); listC.add('Г'); listC.add('Д'); listC.add('Е'); listC.add('Ё');
		 listC.add('Ж'); listC.add('З'); listC.add('И'); listC.add('Й'); listC.add('К'); listC.add('Л'); listC.add('М');
		 listC.add('Н'); listC.add('О'); listC.add('П'); listC.add('Р'); listC.add('С'); listC.add('Т'); listC.add('У');
		 listC.add('Ф'); listC.add('Х'); listC.add('Ц'); listC.add('Ч'); listC.add('Ш'); listC.add('Щ'); listC.add('Ъ');
		 listC.add('Ы'); listC.add('Ь'); listC.add('Э'); listC.add('Ю'); listC.add('Я');
		 
		 for(int i=0,j=0; i<33; i++){
			 final JTextField ch = new JTextField(" "+listC.get(i).toString()+" ");
			 ch.setBorder(BorderFactory.createEmptyBorder(4,10,4,10));
			 ch.setBackground(Color.getHSBColor(130, 150, 140));
			 ch.setFont(TextFont);
			 ch.setHorizontalAlignment(JTextField.CENTER);
			 ch.setEditable(false);
			 ch.addFocusListener(new FocusListener() {

					@Override
					public void focusGained(FocusEvent arg0) {
						if(ch.getBackground() != Color.cyan && !win){
							if(number>0  ){
								int i = adapter.checkLetter(ch.getText());
								if( i != -1){
									list.get(i).setText(ch.getText());
									number--;
									attempt.setText(String.valueOf(number));
									right++;
									if(right == adapter.getAnswer().length()){
										textField.setText("Вы победили!");
										textField.setBackground(Color.green);
										win = true;
									}
								}
								else{ 
									ch.setText("-");
									ch.setBackground(Color.cyan);
									number--;
									attempt.setText(String.valueOf(number));
								}
							}
							else{
								textField.setText("Вы проиграли!");
								textField.setBackground(Color.red);
							}
						}
						frame.requestFocusInWindow();
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						// TODO Auto-generated method stub
						
					}
			            
			        });
			     
			 if(i%16 == 0 ){
				 j++;
			 }
			 if(j>1){
			 panel2.add(ch,new GridBagConstraints(i,j,1,1,6,6,
						GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
						new Insets(5,5,5,5),6,6));
			}
			 else{
				 panel1.add(ch,new GridBagConstraints(i,j,1,1,1,1,
							GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
							new Insets(5,5,5,5),1,1));
			 }
		 }
		 
	     frame.add(panel1,new GridBagConstraints(0,2,2,1,1,1,
					GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(50,5,-1,5),1,1));

	     frame.add(panel2,new GridBagConstraints(0,3,2,1,1,1,
					GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(0,5,0,5),1,1));
	}
	
	private void initWordPanel(){
		 JPanel panel = new JPanel();
		 panel.setBackground(Color.cyan);
		 String FontName = "Impact"; 
		 int style = 0, size = 36;
		 Font TextFont = new Font(FontName, style, size);
		 
		 for(int i=0,j=0; i<answerCount; i++){
			 JTextField ch = new JTextField(" _ ");
			 ch.setBorder(BorderFactory.createEmptyBorder(4,10,4,10));
			 ch.setBackground(Color.getHSBColor(130, 150, 140));
			 ch.setFont(TextFont);
			 ch.setHorizontalAlignment(JTextField.CENTER);
			 ch.setEditable(false);
			 list.add(ch);
			 panel.add(ch,new GridBagConstraints(i,j,1,1,5,5,
						GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
						new Insets(5,5,5,5),5,5));
			
		 }
		 
	     frame.add(panel,new GridBagConstraints(0,1,3,1,1,1,
					GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(30,100,5,100),1,1));
	}
	
	private void initTextFields(){
		 String FontName = "Impact"; 
		 int style = 0, size = 20;
		 Font TextFont = new Font(FontName, style, size);
		 Font TextFont2 = new Font(FontName, style, 59);
		
		 textField = new JTextField(question);
		 textField.setBackground(Color.getHSBColor(130, 150, 140));
		 textField.setFont(TextFont);
		 textField.setHorizontalAlignment(JTextField.CENTER);
		 Insets insets = textField.getInsets();
		 textField.setEditable(false);
	     frame.add(textField,new GridBagConstraints(1,0,1,1,5,5,
					GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(5,50,5,20),4,4));
	     textField.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, 10));
	     
	   
	     attempt = new JTextField(String.valueOf(number));
	     attempt.setBackground(Color.getHSBColor(130, 150, 140));
	     attempt.setFont(TextFont2);
	     attempt.setHorizontalAlignment(JTextField.CENTER);
		 attempt.setEditable(false);
	     frame.add(attempt,new GridBagConstraints(2,2,1,2,1,1,
					GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(50,0,5,5),14,14));
	     attempt.setBorder(BorderFactory.createEmptyBorder(4,35,4,35));
		
	}
	
	private void initButtons(){
	    
			JButton newGame = new JButton("Новая игра");
			newGame.setPreferredSize(new Dimension(100,25));
			//newGame.setMaximumSize(new Dimension(50,25));
				//кнопка добавить
			frame.add(newGame,new GridBagConstraints(0,0,1,1,1,1,
			GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
			new Insets(5,5,5,5),11,11));
			
	        newGame.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	frame.dispose();
	            	Client.getClient();
	            }
	        });
	}
}
