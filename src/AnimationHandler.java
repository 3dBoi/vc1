public class AnimationHandler {

    boolean animationTrigger = false;
    float tweenF = 0.0f;
    boolean direction = true;

    public AnimationHandler(){

    }

    // Tween gets increased until it reaches 1 then it gets reset
    public float animate(){

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

    public void setTweenF(float tweenF) {
        this.tweenF = tweenF;
    }
}
