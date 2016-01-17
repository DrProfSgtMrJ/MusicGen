
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;

import org.jfugue.midi.*;
import org.jfugue.pattern.*;
import org.jfugue.player.Player;

public class MusicPlay {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidMidiDataException 
	 */
	public static void main(String[] args) throws IOException, InvalidMidiDataException {
		// TODO Auto-generated method stub

		Pattern pattern = MidiFileManager.loadPatternFromMidi(new File("Movie_Themes_1492_Conquest_of_Paradise.mid"));
        System.out.println(pattern);
        //Player player = new Player();
        //player.play(pattern);
		String music = pattern.toString();
        
		
		PrintWriter pw = new PrintWriter("musicstring.txt");
		
		
		//System.out.println(music);
		String[] mussplit = music.split(" ");
        	
		for(int i = 0; i<mussplit.length; i++){
			pw.println(mussplit[i]);
		}
        	
       
        pw.close();
        
        
		
	}

	
	
}
