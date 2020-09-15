package design_pattern_project.se02_team07.load_data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 * 더이상 배열 크기를 늘리지 않을, 사이즈 변경이 없을 DB값을 읽어와서 저장하는
 * 클래스로 Aggreate인 DBList를 구상하며, FormalDBIterator의 메소드를 사용한다.
 * Iterator 패턴의 Concrete Aggregate에 해당된다.
 * 
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.22 String[]을 생성하는 클래스가 필요해져서 작성 시작
 *           2019.05.22- 23 createIterator에서 자꾸 return값 오류가 떠서 계속 수정중
 *           2019.05.24 교수님께 return값 여쭤보고 해결완료.
 *           2019.05.26 주석 추가 및 새로 필요한 배열크기 수 추가
 *           2019.05.28 createIterator형식 변경.
 */
public class FormalDB extends Database implements DBList {
    private String filePath = "src/main/resources/texts/pokemon/FormalDex.txt";
    private final String[]list;
    private int lineNum = 0;
    private int rowNum = 0;
    
    /**
     * FormalDBIterator을 사용하여 배열을 읽어들인다.
     * 
     * @return FormalDBIterator
     */
    @Override
    public Iterator<String> createIterator(){
        return new FormalDBIterator(list);
    }

    /**
     * FixedSizeDB 생성자로 txt파일을 한줄씩 읽어들여서 파일줄 수(lineNum) 만큼의 String배열을 만든다.
     */
    public FormalDB() {
        super.setFilePath(filePath);
        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                lineNum++;
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"파일이 없습니다.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"오류 발생 ");
        }
        list = new String[lineNum];
        super.saveDB();
    }

    /**
     * txt파일의 몇번째 줄인지를 나타내는 rowNum변수의 값을 읽어와 배열에 넣는 메소드이다.
     * 
     * @param str 읽어들인 데이터 (한줄 단위로 읽어들임)
     */
    @Override
    protected void makeList(String str){
        /**
         * str 형식 : 도감번호/이름/메인타입/보조타입/종족값/진화여부/총진화단계/현재진화단계
         * string배열에서 행은 한개의 포켓몬에 대한 전체 정보를 나타내고 
         * 열은 전체포켓몬에 대한 도감번호/이름/메인타입/.../현재진화단계를 나타낸다.
         */
        list[rowNum] = str;
        rowNum++;        
    }    
    
}