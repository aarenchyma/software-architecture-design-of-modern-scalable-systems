public class Singleton {

    public static void main(String[] args){

 
        Setting s1= Setting.Get_instance(12, "Dark");

      
        Setting s2= Setting.Get_instance(14, "Light");


        Setting.display();


    }
    
}


class Setting{

   
    private int Font_Size;
    private String theme;


    private Setting(int font, String theme){

        this.Font_Size=font;
        this.theme=theme;
    }

    
    private static Setting Unique_Instance;

    
    public static Setting Get_instance(int font, String theme){

       
        if(Unique_Instance==null){

           
            Unique_Instance= new Setting(font, theme);
        }

        
        return Unique_Instance;


    }

    
    public static void display(){

        System.out.println("The theme is "+Unique_Instance.theme);
        System.out.println("The font size is "+Unique_Instance.Font_Size);
    }


}