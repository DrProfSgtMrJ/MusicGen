
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class GetInformation  {

	public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private static String FILENAME = "Movie_Themes_1492_Conquest_of_Paradise.mid";
    static ArrayList<String> notes = new ArrayList<String>();
    
	/**
	 * @param args
	 */
    
   
    
    public static void main(String[] args) throws Exception {
    	PrintWriter pw = new PrintWriter("MusicInformation.txt");
    	
    	
        Sequence sequence = MidiSystem.getSequence(new File(FILENAME));

        int trackNumber = 0;
        for (Track track :  sequence.getTracks()) {
            trackNumber++;
            pw.println("Track " + trackNumber + ": size = " + track.size());
            pw.println();
            for (int i=0; i < track.size(); i++) { 
                MidiEvent event = track.get(i);
                pw.print("@" + event.getTick() + " ");
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    pw.print("Channel: " + sm.getChannel() + " ");
                    if (sm.getCommand() == NOTE_ON) {
                        int key = sm.getData1();
                        int octave = (key / 12)-1;
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        int velocity = sm.getData2();
                        pw.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);
                    } else if (sm.getCommand() == NOTE_OFF) {
                        int key = sm.getData1();
                        int octave = (key / 12)-1;
                        int note = key % 12;
                        String noteName = NOTE_NAMES[note];
                        notes.add(noteName+octave);
                        int velocity = sm.getData2();
                        pw.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
                    } else {
                        pw.println("Command:" + sm.getCommand());
                    }
                } else {
                    pw.println("Other message: " + message.getClass());
                }
            }

            pw.println();
        }
        pw.close();
        System.out.println(notes.size());
        
        for(int i = 0; i<notes.size(); i++){
        	System.out.println(notes.get(i));
        }
        
        
    }
    
    
   
    
    
    
    
    
    
}


