package src.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileManagement{
    /*
     * Classe que gerencia a leitura e escrita de arquivos
     */
    
    public FileManagement(){
    }

    public ArrayList<String> read(String path) throws Exception{
        /*
         * Função que lê um arquivo e retorna todas as linhas em um ArrayList
         * Necessariamente utiliza o Scanner
         */
        // Abrindo arquivo
        Scanner scanner = null;
        try{
            scanner = new Scanner(new File(path));
        }
        catch (FileNotFoundException e){
            throw new Exception("Erro ao abrir arquivo. Lembre-se que path deve ser relativo da seguinte forma: \"path\\\\to\\\\file.extension\", ou utilize path absoluto");
        }
        catch (Exception e){
            throw new Exception("Erro desconhecido ao abrir arquivo");
        }
        
        // Lendo arquivo
        ArrayList<String> lines = new ArrayList<String>();
        try{
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        }
        catch (NoSuchElementException e){
            scanner.close();
            throw new Exception("Arquivo formatado de forma indevida");
        }
        catch (IllegalStateException e){
            throw new Exception("Erro ao ler arquivo");
        }
        catch (Exception e){
            throw new Exception("Erro desconhecido ao ler arquivo");
        }
        
        // Fechando arquivo
        if(scanner != null){
            scanner.close();
        }
        return lines;
    }
 
    public void write(ArrayList<String> lines, String path) throws Exception{
        /*
         * Função que sobrescreve o conteúdo de um arquivo
         */
        // Abrindo arquivo
        Formatter output = null;
        try{
            output = new Formatter(path);
        }
        catch (FileNotFoundException e){
            throw new Exception("Erro ao abrir arquivo. Lembre-se que path deve ser relativo da seguinte forma: \"path\\to\\file.extension\", ou utilize path absoluto");
        }
        catch (SecurityException e){
            System.err.println( "Sem permissões para abrir o arquivo" );
         System.exit( 1 );
        }
        catch (Exception e){
            throw new Exception("Erro desconhecido ao abrir arquivo");
        }

        // Sobrescrevendo o arquivo
        try{
            for (int i = 0; i < lines.size() - 1; i++) {
                output.format("%s\n", lines.get(i));
            }
            output.format("%s", lines.get(lines.size() - 1));
        }
        catch (NoSuchElementException e){
            output.close();
            throw new Exception("Arquivo formatado de forma indevida");
        }
        catch (FormatterClosedException e){
            throw new Exception("Erro na escrita do arquivo");
        }
        catch (Exception e){
            throw new Exception("Erro desconhecido ao ler arquivo");
        }

        // Fechando arquivo
        if(output != null){
            output.close();
        }

    }

    public void write(String content, String path) throws Exception{
        /*
         * Função que sobrescreve o conteúdo de um arquivo
         */
        // Abrindo arquivo
        Formatter output = null;
        try{
            output = new Formatter(path);
        }
        catch (FileNotFoundException e){
            throw new Exception("Erro ao abrir arquivo. Lembre-se que path deve ser relativo da seguinte forma: \"path\\to\\file.extension\", ou utilize path absoluto");
        }
        catch (SecurityException e){
            System.err.println( "Sem permissões para abrir o arquivo" );
         System.exit( 1 );
        }
        catch (Exception e){
            throw new Exception("Erro desconhecido ao abrir arquivo");
        }

        // Sobrescrevendo o arquivo
        try{
            output.format("%s", content);
        }
        catch (NoSuchElementException e){
            output.close();
            throw new Exception("Arquivo formatado de forma indevida");
        }
        catch (FormatterClosedException e){
            throw new Exception("Erro na escrita do arquivo");
        }
        catch (Exception e){
            throw new Exception("Erro desconhecido ao ler arquivo");
        }

        // Fechando arquivo
        if(output != null){
            output.close();
        }

    }

    public void append(ArrayList<String> lines, String path) throws Exception{
        /*
         * Função que adiciona linhas ao final de um arquivo
         */
        // Lendo o arquivo
        ArrayList<String> fullLines = this.read(path);
        
        // Adicionando no array "fullLines" as linhas passadas como parâmetro
        for (String line : lines) {
            fullLines.add(line);
        }
        
        // Sobrescrevendo o arquivo
        this.write(fullLines, path);
    }

    public void append(String content, String path) throws Exception{
        /*
         * Função que adiciona linhas ao final de um arquivo
         */
        // Lendo o arquivo
        ArrayList<String> fullLines = this.read(path);
        
        // Adicionando no array "fullLines" as linhas passadas como parâmetro
        fullLines.add(content);
        
        // Sobrescrevendo o arquivo
        this.write(fullLines, path);
    }
}
