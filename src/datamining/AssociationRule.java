package datamining;

import representation.BooleanVariable;

import java.util.Set;

/**
 * Cette classe correspond à une règle
 * @param <E>
 */
public class AssociationRule<E> {

    private float confidence;
    private float frequency;
    private Set<BooleanVariable> premise;
    private Set<BooleanVariable> conclusion;

    /**
     * Constructeur
     * @param premise - Premisse
     * @param conclusion - Conclusion
     * @param confidence - Confiance minimale
     * @param frequency - Frequence minimale
     */
    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float confidence, float frequency) {
        this.confidence = confidence;
        this.frequency = frequency;
        this.premise = premise;
        this.conclusion = conclusion;
    }

    /**
     * Getter de confidence
     * @return confidence
     */
    public float getConfidence() {
        return confidence;
    }

    /**
     * Setter de confidence
     * @param confidence - Nouvelle confiance
     */
    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    /**
     * Getter de premise
     * @return premise
     */
    public Set<BooleanVariable> getPremise() {
        return premise;
    }

    /**
     * Setter de premise
     * @param premise - Nouvelle premisse
     */
    public void setPremise(Set<BooleanVariable> premise) {
        this.premise = premise;
    }

    /**
     * Getter de conclusion
     * @return conclusion
     */
    public Set<BooleanVariable> getConclusion() {
        return conclusion;
    }

    /**
     * Setter de conclusion
     * @param conclusion - nouvelle conclusion
     */
    public void setConclusion(Set<BooleanVariable> conclusion) {
        this.conclusion = conclusion;
    }

    /**
     * Getter de frequency
     * @return frequency
     */
    public float getFrequency() {
        return frequency;
    }

    /**
     * Setter de frequency
     * @param frequency - Nouvelle frequence
     */
    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }
}
