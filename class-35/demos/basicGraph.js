function Graph(){
  this.vertexes = [];
}

Graph.prototype.add = function(value){
  this.vertexes.push(value);
};

let studentsIKnow = new Graph();

studentsIKnow.add('Andy');
studentsIKnow.add('Ed');
studentsIKnow.add('Matt');
studentsIKnow.add('Amelia');
studentsIKnow.add('Cristian');
studentsIKnow.add('Seamus');
studentsIKnow.add('Garrett');
studentsIKnow.add('Stephen');
studentsIKnow.add('Barrett');
studentsIKnow.add('James');

