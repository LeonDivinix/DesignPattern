package pattern.action.command;

public class AudioPlayerClient {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        PlayCommand playCommand = new PlayCommand(audioPlayer);
        RewindCommand rewindCommand = new RewindCommand(audioPlayer);
        StopCommand stopCommand = new StopCommand(audioPlayer);
        PauseCommand pauseCommand = new PauseCommand(audioPlayer);

        Keypad keypad = new Keypad();
        keypad.setPlayCommand(playCommand);
        keypad.setRewindCommand(rewindCommand);
        keypad.setStopCommand(stopCommand);
        keypad.setPauseCommand(pauseCommand);


        keypad.play();
        keypad.pause();
        keypad.rewind();
        keypad.play();
        keypad.stop();
    }
}

/**
 * 接收者角色，由录音机类扮演
 * 真正执行命令的类
 */
class AudioPlayer {
    void play() {
        System.out.println("播放ing...");
    }

    void rewind() {
        System.out.println("倒带ing...");
    }

    void stop() {
        System.out.println("停止");
    }

    void pause() {
        System.out.println("暂停ing...");
    }
}

class PlayCommand implements Command {
    private AudioPlayer audioPlayer;

    PlayCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.play();
    }
}

class RewindCommand implements Command {
    private AudioPlayer audioPlayer;

    RewindCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.rewind();
    }
}

class StopCommand implements Command {
    private AudioPlayer audioPlayer;

    StopCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.stop();
    }
}

class PauseCommand implements Command {
    private AudioPlayer audioPlayer;

    PauseCommand(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    @Override
    public void execute() {
        audioPlayer.pause();
    }
}

/**
 * 请求者角色类
 */
class Keypad {
    private Command playCommand;
    private Command rewindCommand;
    private Command stopCommand;
    private Command pauseCommand;

    void setPlayCommand(Command playCommand) {
        this.playCommand = playCommand;
    }

    void setRewindCommand(Command rewindCommand) {
        this.rewindCommand = rewindCommand;
    }

    void setStopCommand(Command stopCommand) {
        this.stopCommand = stopCommand;
    }

    void setPauseCommand(Command pauseCommand) {
        this.pauseCommand = pauseCommand;
    }

    void play() {
        playCommand.execute();
    }

    void rewind() {
        rewindCommand.execute();
    }

    void stop() {
        stopCommand.execute();
    }

    void pause() {
        pauseCommand.execute();
    }
}