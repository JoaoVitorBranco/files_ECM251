package src.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import src.FileManagement;

public class TestFileManagement {
    private FileManagement fileManagement = new FileManagement();
    private String generalPath = "src\\tests";
    @Test 
    public void testRead(){
        try{
            ArrayList<String> lines = fileManagement.read(generalPath + "\\test_file_read.txt");
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
            fileManagement.write(lines,  generalPath + "\\test_file_write.txt");
            ArrayList<String> linesReaded = fileManagement.read( generalPath + "\\test_file_write.txt");
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
            fileManagement.write(lines,  generalPath + "\\test_file_write.txt");
            ArrayList<String> linesReaded = fileManagement.read( generalPath + "\\test_file_write.txt");
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
            String path = generalPath + "\\test_file_append.txt";

            Random rand = new Random();
            int rand_val = rand.nextInt(100); 
            ArrayList<String> lines = new ArrayList<>(Arrays.asList(String.valueOf(rand_val), String.valueOf(rand_val + 1)));

            ArrayList<String> linesBefore = fileManagement.read(path);
            fileManagement.append(lines, path);
            ArrayList<String> linesReaded = fileManagement.read(path);

            assertEquals(linesBefore.size() + lines.size(), linesReaded.size());
            assertEquals(String.valueOf(rand_val), linesReaded.get(linesReaded.size() - 2));
            assertEquals(String.valueOf(rand_val+1), linesReaded.get(linesReaded.size() - 1));

        } catch (Exception err){
            assertTrue(false);
        }
    }
    
    @Test
    public void testWriteContent(){
        try{
            String content = "bananas \n de pijamas";
            fileManagement.write(content,  generalPath + "\\test_file_write.txt");
            ArrayList<String> linesReaded = fileManagement.read( generalPath + "\\test_file_write.txt");
            assertEquals(2, linesReaded.size());
            assertEquals("bananas ", linesReaded.get(0));
            assertEquals(" de pijamas", linesReaded.get(1));


        } catch (Exception err){
            assertTrue(false);
        }
    }

    @Test
    public void testAppendContent(){
        try{
            String path = generalPath + "\\test_file_append.txt";

            Random rand = new Random();
            int rand_val = rand.nextInt(100); 
            String content = String.valueOf(rand_val);

            ArrayList<String> linesBefore = fileManagement.read(path);
            fileManagement.append(content, path);
            ArrayList<String> linesReaded = fileManagement.read(path);

            assertEquals(linesBefore.size() + 1, linesReaded.size());
            assertEquals(String.valueOf(rand_val), linesReaded.get(linesReaded.size() - 1));

        } catch (Exception err){
            assertTrue(false);
        }
    }

}
