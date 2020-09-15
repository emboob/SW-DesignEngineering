package design_pattern_project.se02_team07.load_data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import javax.swing.JOptionPane;

/**
 * DB를 만드는 클래스
 *
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.17 첫 작성 시작.
 *           2019.05.18 savePokeinfo : String을 매개변수나, 클래스로 넘겨받지 않고 임의로 설정해준 상태. 
 *           2019.05.20 String 입력을 받아서 arrayList에 저장해줌. DB에 관련한 클래스를 하나로 재정렬하고 iterator 추가함. 
 *           2019.05.21 클래스를 전체적으로 정리하고, pokeInfoSaver인터페이스 추가 및 컴포지트 패턴 적용 시도
 *           2019.05.22 컴포지트는 패턴 상황에 맞지 않는것 같아서 삭제, 
 *           2019.05.23 이터레이터 패턴에 맞춰서 클래스 재정렬 및 수정  
 *           2019.05.27 다른 클래스들과 연결하기 위해 클래스 수정. seperator메소드 지우고 DBAdmin에서 작업옮겨줌.
 */
public abstract class Database {
    private String filePath;
    
    /**
     * 파일 경로를 저장하는 메소드
     * @param filePath 입력받은 파일경로
     */
    protected void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * 저장된 데이터를 경로에 따라 읽어오는 메소드.
     */
    public void saveDB() {
        Path path = Paths.get(filePath);
        
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                makeList(line);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"파일이 없습니다.");
        } catch (IOException ee) {
            JOptionPane.showMessageDialog(null,"오류 발생");
        }
    }
    
    /**
     * 상속하여 구현할 포켓몬 리스트를 만드는 메소드
     * 
     * @param str 읽어들인 데이터 (한줄 단위로 읽어들임)
     */
    protected abstract void makeList(String str);
    
}
