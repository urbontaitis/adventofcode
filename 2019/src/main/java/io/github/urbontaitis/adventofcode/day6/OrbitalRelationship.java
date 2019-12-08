package io.github.urbontaitis.adventofcode.day6;

class OrbitalRelationship {
    private String source;
    private String target;

    OrbitalRelationship(String orbitalRelationship) {
        String[] relationship = orbitalRelationship.split("\\)");
        this.source = relationship[0];
        this.target = relationship[1];
    }

    String getSource() {
        return source;
    }

    String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "source='" + source + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}
