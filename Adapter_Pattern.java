

interface Modern_Media_Player {

    void Play(String File);
    void Pause();
    void Play2x();
}

class Mp3_Player implements Modern_Media_Player {

    public void Play(String File) {
        System.out.println("The audio " + File + " is running now.");
    }

    public void Pause() {
        System.out.println("The audio is paused.");
    }

    public void Play2x() {
        System.out.println("The audio is being played with 2x speed.");
    }
}

class Legacy_Media_Player {

    void Start_Music(String file) {
        System.out.println("The " + file + " is playing now.");
    }

    void Stop_Music() {
        System.out.println("The track is stopped now.");
    }
}

class Media_Adapter implements Modern_Media_Player {

    private Legacy_Media_Player O_Player;

    public Media_Adapter(Legacy_Media_Player O_Player) {
        this.O_Player = O_Player;
    }

    public void Play(String File) {
        O_Player.Start_Music(File);
    }

    public void Pause() {
        O_Player.Stop_Music();
    }

    public void Play2x() {
        System.out.println("This feature is not supported by the Legacy_Media_Player.");
    }
}

public class Adapter_Pattern {

    public static void main(String[] args) {

        Modern_Media_Player M_Player = new Mp3_Player();

        M_Player.Play("Noor Jahan Heart touching Ghazal");

        Modern_Media_Player Player1 = new Media_Adapter(new Legacy_Media_Player());

        Player1.Play("Rahat Fat Ali Khan's MBZ");
    }
}
