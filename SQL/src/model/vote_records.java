package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class vote_records {
    private int id;
    private String user_id;
    private int vote_num;
    private int status;
    private Date create_time;
    vote_records(){}
    vote_records(int id,String user_id,int vote_num,int status,Date create_time){
        this.id=id;
        this.vote_num=vote_num;
        this.user_id=user_id;
        this.status=status;
        this.create_time=create_time;
    }

    public int getId(){
        return id;
    }
    public String getUser_id(){
        return user_id;
    }
    public int getVote_num(){
        return vote_num;
    }
    public int getStatus(){
        return status;
    }
    public Date getCreate_time(){
        return create_time;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUser_id(String user_id){
        this.user_id=user_id;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setVote_num(int vote_num){
        this.vote_num=vote_num;
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年-MM月-dd日");
        return "id:"+id+" 用户id："+user_id+" 投票数"+vote_num+" 状态"+status+" 记录日期："+sf.format(create_time);
    }
}
