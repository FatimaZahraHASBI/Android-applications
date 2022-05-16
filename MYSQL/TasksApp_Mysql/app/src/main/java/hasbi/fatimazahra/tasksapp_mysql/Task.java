package hasbi.fatimazahra.tasksapp_mysql;

public class Task {
    private int id;
    private String name;

    public Task(){}

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Task(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
