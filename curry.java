import java.io.*;
 
public class curry { 
	static String[] line = new String[2400];	//row text
	static String[] st = new String[200];	//names of spice
	static int line_n = 0;	//number of line
	static int n = 0;	// number of names
	static String[] stack = new String[200];
	static int stack_n = 0;
	
    public static void main( String[] args ) {
		int[] ok = new int[200]; // 0:not yet, 1: checking, 2 : checked , 3 : prepare
		int fin = 0;
		int step = 0;
			try{
				FileReader f = new FileReader("blendlist.txt");
				BufferedReader b = new BufferedReader(f);
				String s;
				while((s = b.readLine())!=null){
					line[line_n] = s;
					line_n++;
					String[] _line = s.split(" ");
					step:for(int j=0;j<2;j++){
						for(int i=0;i<n;i++){
							if(st[i].equals(_line[j])){
								continue step;
							}
						}
						st[n] = _line[j];
						n++;
					}
				}
			
				for(int i=0;i<n;i++){
					ok[i] = 0;
				}
				ok[0] = 1;
				
				stack[0] = st[0];
				stack_n = 1;
				
				wr(ok);
			}catch(Exception e){
				System.out.println("error");
			}
    }
	
	
	public static void wr(int[] _ok){
		int fin = 0;
		int count = 0;
		int counter[] = new int[7];
		counter[0] = 1;
		while(fin == 0){
			count++;
			fin = 1;
			for(int i=0;i<n;i++){
				if(_ok[i] == 1){
					_ok[i] = 2;
					for(int j=0;j<line_n;j++){
						String[] _line = line[j].split(" ");
						if (st[i].equals(_line[0])){
							for(int k=0;k<n;k++){
								if(_line[1].equals(st[k]) && _ok[k] == 0){
									_ok[k] = 3;	//prepare
									counter[count]++;
								}
							}
						}
						else if(st[i].equals(_line[1])){
							for(int k=0;k<n;k++){
								if(_line[0].equals(st[k]) && _ok[k] == 0){
									_ok[k] = 3;
									counter[count]++;
								}
							}
						}
					}
				}
			}
			for(int k=0;k<n;k++){
				if(_ok[k] != 2) fin = 0;
				if(_ok[k] == 3){
					if(count == 4) System.out.println(st[k]);
					_ok[k] = 1;
					stack[stack_n] = st[k];
					stack_n++;
				}
			}
			cal();
		}
		
		for(int i=0;i<7;i++){
			System.out.println(counter[i]);
		}
	}
	
	public static void cal(){
		int point = 0;
		int tmp;
		for(int i=0;i<line_n;i++){
			tmp = 0;
			String[] _line = line[i].split(" ");
			for(int j=0;j<stack_n;j++){
				if (stack[j].equals(_line[0])){
					tmp++;
				}
				else if(stack[j].equals(_line[1])){
					tmp++;
				}
			}
			if(tmp == 0 || tmp == 2){
				point++;
			}
		}
	}
}