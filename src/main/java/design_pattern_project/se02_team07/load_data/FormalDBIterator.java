package design_pattern_project.se02_team07.load_data;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator 패턴의 Concrete Iterator 역할을 수행하는 클래스
 * 
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.24 첫작성
 *           2019.05.27 다른 클래스와 연결하기 위해서 수정
 *           2019.05.28 next형식 변경, Iterator인터페이스를 상속받도록 변경
 * 
 */
public class FormalDBIterator implements Iterator<String>{
    private final String[] list;
    private int position = 0;
    
    /**
     * FormalDBIterator의 생성자.
     * 
     * @param list 
     */
    FormalDBIterator(String[] list){
    this.list = list;
    }
    
    /**
     * java.util.Iterator을 구상한 메소드로서 배열의 값을 증가시켜 hasNext를
     * 사용하게 한다.
     * 
     * @return 증가된 배열 위치
     */
    @Override
    public String next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        position = position+1;
        return list[position-1];
    }
    
    /**
     * java.util.Iterator을 구상한 메소드로서 다음 배열 위치가 가진 값을
     * 가져온다.
     * 
     * @return 증가된 배열에서의 값
     */
    @Override
    public boolean hasNext(){
        return !(position >= list.length || list[position] == null);
    }
    
}

