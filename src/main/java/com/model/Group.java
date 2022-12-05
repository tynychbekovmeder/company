package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private String dateOfStart;

    @Column(name = "date_of_finish")
    private String dateOfFinish;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "groupList")
    private List<Course> courseList;

    @Transient
    private Company company;

    public void setCourse(Course course) {
        if (courseList == null) {
            courseList = new ArrayList<>();
        }
        this.courseList.add(course);
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "group")
    private List<Student> studentList;

    public Group(String groupName, String dateOfStart, String dateOfFinish) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", dateOfStart='" + dateOfStart + '\'' +
                ", dateOfFinish='" + dateOfFinish + '\'' +
                '}';
    }
}
