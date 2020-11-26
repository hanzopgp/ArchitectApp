package datamining;

import representation.BooleanVariable;

import java.util.Set;

public class AssociationRule<E> {

    private float confidence;
    private float frequency;
    private Set<BooleanVariable> premise;
    private Set<BooleanVariable> conclusion;

    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float confidence, float frequency) {
        this.confidence = confidence;
        this.frequency = frequency;
        this.premise = premise;
        this.conclusion = conclusion;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public Set<BooleanVariable> getPremise() {
        return premise;
    }

    public void setPremise(Set<BooleanVariable> premise) {
        this.premise = premise;
    }

    public Set<BooleanVariable> getConclusion() {
        return conclusion;
    }

    public void setConclusion(Set<BooleanVariable> conclusion) {
        this.conclusion = conclusion;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }
}
