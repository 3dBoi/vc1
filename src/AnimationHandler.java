public class AnimationHandler {

    boolean animationTrigger = false;
    float tweenF = 0.0f;
    boolean direction = true;

    public AnimationHandler(){

    }

    public float animate(){

        // VLLT HIER IWAS FALSCH??
        if(tweenF<1&&direction){
            tweenF=tweenF+0.01f;
        }else if(tweenF>=1) {
            direction = false;
            tweenF = 0.0f;
        }

        return tweenF;
    }


    public void setAnimationTrigger(boolean animationTrigger) {
        this.animationTrigger = animationTrigger;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public boolean getAnimationTrigger() {
        return animationTrigger;
    }

    public boolean getDirection() {
        return direction;
    }
}
