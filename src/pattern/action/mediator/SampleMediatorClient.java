package pattern.action.mediator;

public class SampleMediatorClient {
    public static void main(String[] args) {
        MotherBoard motherBoard = new MotherBoard();
        CDDriver cdDriver = new CDDriver(motherBoard);
        CPU cpu = new CPU(motherBoard);
        VideoCard videoCard = new VideoCard(motherBoard);
        SoundCard soundCard = new SoundCard(motherBoard);

        motherBoard.setCdDriver(cdDriver);
        motherBoard.setCpu(cpu);
        motherBoard.setVideoCard(videoCard);
        motherBoard.setSoundCard(soundCard);

        cdDriver.readCD();
    }
}

class MotherBoard implements SampleMediator {
    private CDDriver cdDriver;
    private CPU cpu;
    private VideoCard videoCard;
    private SoundCard soundCard;

    public void setCdDriver(CDDriver cdDriver) {
        this.cdDriver = cdDriver;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }

    @Override
    public void change(SampleColleague colleague) {
        if (colleague instanceof CDDriver) {
            dealCDData((CDDriver) colleague);
        }
        else if (colleague instanceof CPU) {
            dealCPUData((CPU) colleague);
        }
    }

    private void dealCDData(CDDriver driver) {
        cpu.excuteData(driver.getData());
    }

    private void dealCPUData(CPU cpu) {
        videoCard.videoData(cpu.getVideoData());
        soundCard.soundData(cpu.getSoundData());
    }
}

class SampleColleague {
    private SampleMediator mediator;

    public SampleColleague(SampleMediator mediator) {
        this.mediator = mediator;
    }

    public SampleMediator getMediator() {
        return mediator;
    }
}

interface SampleMediator {
    void change(SampleColleague colleague);
}

class CDDriver extends SampleColleague {
    private String data;
    public CDDriver(SampleMediator mediator) {
        super(mediator);
    }

    public String getData() {
        return data;
    }

    public void readCD() {
        data = "One piece,I'll be the king.";
        getMediator().change(this);
    }
}

class CPU extends SampleColleague {
    private String soundData;
    private String videoData;
    public CPU(SampleMediator mediator) {
        super(mediator);
    }

    public String getSoundData() {
        return soundData;
    }

    public String getVideoData() {
        return videoData;
    }

    public void excuteData(String data) {
        String[] dataAry = data.split(",");
        videoData = dataAry[0];
        soundData = dataAry[1];
        getMediator().change(this);
    }
}

class VideoCard extends SampleColleague {
    public VideoCard(SampleMediator mediator) {
        super(mediator);
    }

    public void videoData(String data) {
        System.out.println("您正在观看的是：" + data);
    }
}

class SoundCard extends SampleColleague {
    public SoundCard(SampleMediator mediator) {
        super(mediator);
    }

    public void soundData(String data) {
        System.out.println("画外音：" + data);
    }
}