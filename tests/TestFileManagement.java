package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.tools.ForwardingJavaFileManager;

import org.junit.Test;

import src.FileManagement;

public class TestFileManagement {
    private FileManagement fileManagement = new FileManagement();
    @Test 
    public void testRead(){
        try{
            ArrayList<String> lines = fileManagement.read("C:\\Users\\joaov\\Desktop\\EDCII\\Linguagens de Programa\u00E7\u00E3o I\\files_ECM251\\tests\\test_file_read.txt");
            assertEquals(lines.size(), 3);
            assertEquals(lines.get(0), "linha0");
            assertEquals(lines.get(1), "linha1");
            assertEquals(lines.get(2), "linha2");
        } catch (Exception err){
            assertEquals(err.getMessage(), true);
        }
    }

    @Test
    public void testWrite(){
        try{
            ArrayList<String> lines = new ArrayList<>(
                Arrays.asList(
                    "banana1",
                    "banana2",
                    "banana3"
                )
            );
            fileManagement.write(lines, "C:\\Users\\joaov\\Desktop\\EDCII\\Linguagens de Programa\u00E7\u00E3o I\\files_ECM251\\tests\\test_file_write.txt");
            ArrayList<String> linesReaded = fileManagement.read("C:\\Users\\joaov\\Desktop\\EDCII\\Linguagens de Programa\u00E7\u00E3o I\\files_ECM251\\tests\\test_file_write.txt");
            assertEquals(lines.size(), linesReaded.size());
            for (int i = 0; i < lines.size(); i++) {
                assertEquals(lines.get(i), linesReaded.get(i));
            }

        } catch (Exception err){
            assertTrue(false);
        }
    }

    @Test
    public void testWriteOverwrite(){
        try{
            ArrayList<String> lines = new ArrayList<>(
                Arrays.asList(
                    "maca1",
                    "maca2",
                    "maca3",
                    "casa1",
                    "casa2",
                    "casa3"
                )
            );
            fileManagement.write(lines, "C:\\Users\\joaov\\Desktop\\EDCII\\Linguagens de Programa\u00E7\u00E3o I\\files_ECM251\\tests\\test_file_write.txt");
            ArrayList<String> linesReaded = fileManagement.read("C:\\Users\\joaov\\Desktop\\EDCII\\Linguagens de Programa\u00E7\u00E3o I\\files_ECM251\\tests\\test_file_write.txt");
            assertEquals(lines.size(), linesReaded.size());
            for (int i = 0; i < lines.size(); i++) {
                assertEquals(lines.get(i), linesReaded.get(i));
            }

        } catch (Exception err){
            assertTrue(false);
        }
    }

    @Test
    public void testAppend(){
        try{
            String path ="C:\\Users\\joaov\\Desktop\\EDCII\\Linguagens de Programa\u00E7\u00E3o I\\files_ECM251\\tests\\test_file_append.txt";

            Random rand = new Random();
            int rand_val = rand.nextInt(100); 
            ArrayList<String> lines = new ArrayList<>(Arrays.asList(String.valueOf(rand_val)));

            ArrayList<String> linesBefore = fileManagement.read(path);
            fileManagement.append(lines, path);
            ArrayList<String> linesReaded = fileManagement.read(path);

            assertEquals(linesBefore.size() + lines.size(), linesReaded.size());
            assertEquals(String.valueOf(rand_val), linesReaded.get(linesReaded.size() - 1));

        } catch (Exception err){
            assertTrue(false);
        }
    }
    
}
