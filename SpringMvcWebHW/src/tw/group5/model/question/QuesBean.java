package tw.group5.model.question;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Questions")
public class QuesBean {

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int q_ID;
    private String q_Type;
    private String q_Ques;
    private String q_Selection;
    private String q_Ans;
    private String p_Class;

	public QuesBean() {
	}

    
    //Constructor
    public QuesBean(int q_ID, String q_Type, String q_Ques, String q_Selection, String q_Ans, String p_Class) {
      //?
      super();
      this.q_ID = q_ID;
      this.q_Type = q_Type;
      this.q_Ques = q_Ques;
      this.q_Selection = q_Selection;
      this.q_Ans = q_Ans;
      this.p_Class = p_Class;
    }
    	   
	public int getQ_ID() {
		return q_ID;
	}
	public void setQ_ID(int q_ID) {
		this.q_ID = q_ID;
	}


	public String getQ_Type() {
		return q_Type;
	}
	public void setQ_Type(String q_Type) {
		this.q_Type = q_Type;
	}


	public String getQ_Ques() {
		return q_Ques;
	}
	public void setQ_Ques(String q_Ques) {
		this.q_Ques = q_Ques;
	}


	public String getQ_Selection() {
		return q_Selection;
	}
	public void setQ_Selection(String q_Selection) {
		this.q_Selection = q_Selection;
	}


	public String getQ_Ans() {
		return q_Ans;
	}
	public void setQ_Ans(String q_Ans) {
		this.q_Ans = q_Ans;
	}


	public String getP_Class() {
		return p_Class;
	}
	public void setP_Class(String p_Class) {
		this.p_Class = p_Class;
	}


	//??
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuesBean [q_ID=");
		builder.append(q_ID);
		builder.append(", q_Type=");
		builder.append(q_Type);
		builder.append(", q_Ques=");
		builder.append(q_Ques);
		builder.append(", q_Selection=");
		builder.append(q_Selection);
		builder.append(", q_Ans=");
		builder.append(q_Ans);
		builder.append(", p_Class=");
		builder.append(p_Class);
		builder.append("]");
		return builder.toString();
	}
}