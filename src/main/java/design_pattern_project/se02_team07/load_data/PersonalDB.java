package design_pattern_project.se02_team07.load_data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 배열 크기가 늘어나며, 사이즈 변경이 있을 DB를 저장하는 클래스로
 * Aggreate인 DBList를 구상하며, java.util.Iterator의 메소드를 사용한다.
 * Iterator 패턴의 Concrete Aggregate에 해당된다.
 * 
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.20 첫 작성 . String 입력을 받아서 arrayList에 저장해줌.
 *           2019.05.21 savePokeinfo에서 saveDB, seperator등으로 메소드를 나눔.
 *           2019.05.22 ~ 23 이터레이터 패턴에 맞춰서 클래스 재정렬 및 수정
 *           2019.05.28 DBList상속 받도록 변경, createIterator형식 변경
 */
public class PersonalDB extends Database implements DBList {
    private final String filePath;
    private final List<String> list = new ArrayList<>();
    
/**
 * 클래스 생성자, 클래스 변수를 저장하고 변수내용에 따라 DB를 저장해준다.
 * 
 * @author 신휘정
 * @param path 유저의 개인도감 경로
 */    
    public PersonalDB(String path){
        filePath = path;
        
        super.setFilePath(filePath);
        super.saveDB();
    }
    
    /**
     * java.util.Iterator에 있는 arrayList용 이터레이터를 사용하는 메소드
     * 
     * @return list.iterator(); 형식의 arrayList 읽는 형태
     */
    @Override
    public Iterator<String> createIterator(){
        return list.iterator();
    }
    
    /**
     * ArrayList에 읽어들인 str에서 DBcolumn번째 열을 저장하는 메소드
     * @author 신휘정
     * @param str : DB에서 읽어들인 데이터
     */
    @Override
    protected void makeList(String str){
    list.add(str);
    }
    
}
