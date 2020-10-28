package menu2;

public class MenuNode {
		String label;
		String link;
		public MenuNode(String label,String link){
			this.label = label;
			this.link = link;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
	}

