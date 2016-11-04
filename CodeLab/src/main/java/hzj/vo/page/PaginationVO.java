package hzj.vo.page;

public class PaginationVO {
	private int page;
	private int size;
	private int total;
	public int getPage() {
		if(page <= 0){
			return 1;
		}
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		if(size <= 0){
			return 5;
		}
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
