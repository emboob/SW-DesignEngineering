package design_pattern_project.se02_team07.load_data;

import java.util.Iterator;

/**
 * Iterator 패턴의 Aggregate에 해당하는 인터페이스
 * 
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.17 인터페이스 작성.
 */
public interface DBList {
    public Iterator<String> createIterator();
    
}
