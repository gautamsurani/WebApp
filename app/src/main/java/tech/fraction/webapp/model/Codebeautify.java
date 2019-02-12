package tech.fraction.webapp.model;

public class Codebeautify {
 Paging PagingObject;
 SearchField SearchFieldObject;


 // Getter Methods 

 public Paging getPaging() {
  return PagingObject;
 }

 public SearchField getSearchField() {
  return SearchFieldObject;
 }

 // Setter Methods 

 public void setPaging(Paging pagingObject) {
  this.PagingObject = pagingObject;
 }

 public void setSearchField(SearchField searchFieldObject) {
  this.SearchFieldObject = searchFieldObject;
 }
}