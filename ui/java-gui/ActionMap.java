import org.w3c.dom.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class ActionMap{
	private LinkedList ActionList[] = new LinkedList[11];
	ActionMap()
	{
		for(int i=0; i<11; i++)
			ActionList[i] = new LinkedList();
	}
	public int DeleteProcess(int pid)
	{
		if(pid >= 0 && pid <= 10) {
			ActionList[pid].killList();
			return 0;
		} else return 1;	
		
	}
	
	public int AddAction(int pid, Element action, int offset)
	{
		ActionList[pid].insertElement(action, offset);
		return 0;
	}
        public String getModelName(int pid)
        {
            return ActionList[pid].getHead().getElement().getAttribute("model");
        }
	public void UpdateAction(int pid, Element action)
	{
		ActionList[pid].updateElement(action);
	}
	public Element getActionByName(int pid, String actionName)
	{
		return ActionList[pid].find(actionName);
	}
	public LinkNode getLNodeByName(int pid, String actionName)
	{
		LinkNode n = ActionList[pid].findNode(actionName);
		return n; 
	}
	public int numActiveProcesses()
	{
		int count = 0;
		for (int i=0; i<11; i++)
		{
			if(ActionList[i].getHead() !=null)
				count++;
		}
		
		return count;
	}
        public DefaultMutableTreeNode getReadyActionList()
        {
            String stateValue;
            DefaultMutableTreeNode root = 
                new DefaultMutableTreeNode("Process List");
            DefaultMutableTreeNode[] pids = 
                new DefaultMutableTreeNode[11];
            for (int i=0; i< 11; i++)
            {
                if (this.isProcActive(i))
                {
                    pids[i] = new DefaultMutableTreeNode(this.getModelName(i));               
                    root.add(pids[i]);                
                    LinkNode curr=this.getCurrentLink(i);
                    this.reset(i);
                    LinkNode probe = this.getCurrentLink(i);
                
                    while (probe != null)
                    {
                        Element payload = probe.getElement();                    
                        if ( payload.getAttribute("state") != null )
                        {
                            stateValue=payload.getAttribute("state");
                            if (stateValue.equals("AVAILABLE") 
                                || stateValue.equals("READY")
                                || stateValue.equals("RUN"))
                            {
                                DefaultMutableTreeNode leaf = 
                                    new DefaultMutableTreeNode(payload.getAttribute("name") 
                                        + "(" +i + ")");
                                pids[i].add(leaf);
                            }
                        }
                        
                        probe = probe.getNext();
                    }
                    if (pids[i].getChildCount()==0)
                        {
                            pids[i].add(new DefaultMutableTreeNode("No Available Actions"));
                        }
                    this.setCurrent(i,curr);
                }
                
            }
            
            
            return root;
            
            
        }
	public boolean[] listActiveProcesses()
	{
		boolean[] apList= new boolean[11];
		for (int i=0; i<11; i++)
		{
			if(ActionList[i].getHead() !=null)
				apList[i]=true;
			else apList[i]=false;
		}
		return apList;
	}
	public void nextAction(int pid)
	{
		this.getNextActionDetails(pid);
	}
	public void nextAction2(int pid)
	{
		ActionList[pid].getNext();
	}
	public void prevAction(int pid)
	{
		this.getPrevActionDetails(pid);
	}
	public void reset(int pid)
	{
		ActionList[pid].resetCurr();
	}
	public void setToFirstAction(int pid)
	{
		ActionList[pid].setCurrToFirstAction();
	}
	public Element getCurrentAction(int pid)
	{
		String actionName=ActionList[pid].getElement().getAttribute("name");
		return this.getActionByName(pid,actionName);
//		return ActionList[pid].getElement();
	}
	public void setCurrent(int pid, LinkNode newCurr)
	{
		ActionList[pid].setCurrentLink(newCurr);
	}
	public String printCurrentName(int pid)
	{
		
		return ActionList[pid].getElement().getAttribute("name");  
	}
	public LinkNode getCurrentLink(int pid)
	{
		return ActionList[pid].getCurr();
	}
        public boolean isProcActive(int pid)
        {
            return ActionList[pid].isProcActive();
        }
	
	public boolean isNextOk(int pid)
	{
		LinkNode current=this.getCurrentLink(pid);
		LinkNode temp = ActionList[pid].getNext();
		while (temp != null && !temp.getElement().getLocalName().equals("action"))
			temp = ActionList[pid].getNext();	

		ActionList[pid].setCurr(current.getElement()); // reset current to original
		if (temp == null)
		{
			return false;
		}
		else return true;
	}

	public boolean isPrevOk(int pid)
	{
		LinkNode current = this.getCurrentLink(pid);
		LinkNode temp = ActionList[pid].getPrev();
		while (temp !=null && !temp.getElement().getLocalName().equals("action"))
			temp=ActionList[pid].getPrev();
		ActionList[pid].setCurr(current.getElement());
		if (temp == null)
			return false;
		else return true;
	}

	public LinkNode getPrevActionDetails(int pid)
	{
		if (!this.isPrevOk(pid))
			return null;

		LinkNode temp = ActionList[pid].getPrev();
		while (temp != null && !temp.getElement().getLocalName().equals("action"))
		{
			temp = ActionList[pid].getPrev();
		}
		return temp;
	}
	public LinkNode getNextActionDetails(int pid)
	{
		if (!this.isNextOk(pid))
			return null;

		LinkNode temp = ActionList[pid].getNext();
		while (temp != null && !temp.getElement().getLocalName().equals("action"))
			temp = ActionList[pid].getNext();
		return temp;	
	}

	public void chooseAction(Element action, int pid)
	{
		ActionList[pid].setCurr(action);
	}
	public void Print()
	{
		LinkNode curr;
		for(int i=0; i<11; i++)
		{                        
			LinkNode currHolder=ActionList[i].getCurr();                        
			ActionList[i].resetCurr();
			curr = ActionList[i].getCurr();
                        if (currHolder == null)
                            currHolder=curr;
			while(curr !=null)
			{
				if (currHolder.getElement().getAttribute("name").equals
					(curr.getElement().getAttribute("name")))

					System.out.print("curr->");
				System.out.println(i + ":  " 
				+ curr.getElement().getAttribute("name")
				+ curr.getElement().getAttribute("state"));
				curr = curr.getNext();
			}
			ActionList[i].setCurrentLink(currHolder);
		}
	}
}
