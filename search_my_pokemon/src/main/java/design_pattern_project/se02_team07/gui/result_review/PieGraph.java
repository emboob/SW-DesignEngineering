package design_pattern_project.se02_team07.gui.result_review;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;

/**
 * 이 클래스는 '파이 그래프 클래스' 입니다.
 * 
 * @author  20173116 신휘정
 * @version 1.0.0
 * @since   2019.05.10 파이그래프 작성시작 및 완료
 *           2019.05.19 파이그래프의 바탕을 Container로 변경  
 *           2019.05.21 파이그래프를 JPanel위에 설정한것을 뺌
 *           2019.05.30 Slice의 값을 정렬하기 위해 템플릿 메소드를 적용함
 *           2019.05.31 소나 큐브 오류 수정
 *           2019.06.02 주석 추가
 */
public final class PieGraph extends JComponent {
    transient Slice[] slices = new Slice[18];
    
    /**
     * 파이그래프의 생성자로 자르는 갯수들을 받아서 세팅한다.
     * @param typeNum 여러 타입의 숫자를 세어 저장하는 배열
     */
    PieGraph(int[] typeNum) {
        setSlices(typeNum);
        Arrays.sort(slices);
    }
    
    /**
     * 자르는 비율을 세팅하는 메소드로 여기서 자를 비율을 설정하면 해당 값을
     * 받아서 템플릿 메소드 패턴을 수행할 준비를 마친다.
     * 
     * @param typeNum 여러 타입들에 해당하는 배열 번호 순서
     */
    public void setSlices(int[] typeNum) {
        slices[0] = new Slice(typeNum[0], new Color(204,204,051));
        slices[1] = new Slice(typeNum[1], new Color(102,051,051));
        slices[2] = new Slice(typeNum[2], new Color(153,051,255));
        slices[3] = new Slice(typeNum[3], new Color(255,255,000));
        slices[4] = new Slice(typeNum[4], new Color(255,204,255));
        slices[5] = new Slice(typeNum[5], new Color(153,051,051));
        slices[6] = new Slice(typeNum[6], new Color(255,153,000));
        slices[7] = new Slice(typeNum[7], new Color(204,153,204));
        slices[8] = new Slice(typeNum[8], new Color(102,051,153));
        slices[9] = new Slice(typeNum[9], new Color(000,153,000));
        slices[10] = new Slice(typeNum[10], new Color(204,153,051));
        slices[11] = new Slice(typeNum[11], new Color(153,255,255));
        slices[12] = new Slice(typeNum[12], new Color(204,204,153));
        slices[13] = new Slice(typeNum[13], new Color(153,051,153));
        slices[14] = new Slice(typeNum[14], new Color(255,000,102));
        slices[15] = new Slice(typeNum[15], new Color(153,102,000));
        slices[16] = new Slice(typeNum[16], new Color(102,153,153));
        slices[17] = new Slice(typeNum[17], new Color(051,102,255));
    }
    
    /**
     * 파이그래프를 화면에 그려주는 메소드
     * 
     * @param g 그래픽스 메소드를 사용한다는 표시
     */
    @Override
    public void paint(Graphics g) {
        drawPie((Graphics2D) g, slices);
    }
    
    /**
     * 파이 그래프의 세팅된 slice를 전체 비율에 따라 설정하는 메소드로 여기서
     * Slice 클래스에서 적용시킨 템플릿 메소드 패턴을 해당 상황에서 
     * 큰 값 순서대로 앞으로 정렬시켜 구현한다.
     * 
     * @param g 그래픽스 메소드를 사용한다는 표시
     * @param slices 세팅한 slice
     */
    void drawPie(Graphics2D g, Slice[] slices) {
        double total = 0.0D;
        
        for (Slice slice : slices) {
            total += slice.value;
        }
        
        double curValue = 0.0D;
        int startAngle = 0;
        for (Slice slice : slices) {
            if(total != 0) {
                startAngle = (int) (curValue * 360 / total);
            } else {
                total = 0.01;
            }
            int arcAngle = (int) (slice.value * 360 / total);
            g.setColor(slice.color);
            g.fillArc(0 , 0, 300, 300, startAngle, arcAngle);
            curValue += slice.value;
        }
    }
    
}

/**
 * 파이그래프를 구성할 슬라이스를 구성하는 클래스로
 * 파이 그래프를 입력된 값에 따라 자르기 위하여 사용하며
 * java.lang.compareable 인터페이스를 상속받아 템플릿 메소드 패턴을
 * 사용한다.
 */
class Slice implements Comparable<Slice> {
    /**
     * value = 파이그래프에서 차지할 비율
     * color = 그래프에 그릴 부분의 색
     */
    double value;
    Color color;
    
    /**
     * Slice 클래스의 생성자로 값을 설정함.
     * 
     * @param value 파이그래프에서 차지할 비율
     * @param color  그래프에 그릴 부분의 색
     */
    public Slice(double value, Color color) {  
        this.value = value;
        this.color = color;
    }
    
    /**
     * java.lang.compareable을 구현하는 CompareTo 메소드로 안의 값에 따라
     * 값을 다르게 리턴하여 내부의 값을 정렬한다.
     * 
     * @param otherSlice 
     * @return status
     */
    @Override
    public int compareTo(Slice otherSlice){
        int status = 1;
        if(this.value < otherSlice.value){
            status = -1;
        } else if (this.value == otherSlice.value){
            status = 0;
        }
        
        return status;
    }
    
    /**
     * 값을 정렬시 같다면 굳이 다시 소팅할 필요가 없다는것을 설정하는 메소드
     * 
     * @param obj
     * @return 값 변경 없음
     */
    @Override
    public boolean equals(Object obj) {
        return false;
    }
    
    /**
     * 넷빈즈가 자동으로 입력해준 메소드
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.color);
        
        return hash;
    }
    
}

