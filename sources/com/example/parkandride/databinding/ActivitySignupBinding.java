package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.example.parkandride.R;

public final class ActivitySignupBinding implements ViewBinding {
    public final TextView appName;
    public final EditText emailSignup;
    public final LinearLayout emailView;
    public final ImageView imageView;
    public final LinearLayout ll;
    public final TextView logIn;
    public final ImageView logo;
    public final LinearLayout password;
    public final LinearLayout password2;
    public final EditText passwordSignup;
    public final EditText person;
    public final LinearLayout personName;
    public final LottieAnimationView progressSignup;
    public final RelativeLayout relative;
    private final RelativeLayout rootView;
    public final AppCompatButton signup;

    private ActivitySignupBinding(RelativeLayout rootView2, TextView appName2, EditText emailSignup2, LinearLayout emailView2, ImageView imageView2, LinearLayout ll2, TextView logIn2, ImageView logo2, LinearLayout password3, LinearLayout password22, EditText passwordSignup2, EditText person2, LinearLayout personName2, LottieAnimationView progressSignup2, RelativeLayout relative2, AppCompatButton signup2) {
        this.rootView = rootView2;
        this.appName = appName2;
        this.emailSignup = emailSignup2;
        this.emailView = emailView2;
        this.imageView = imageView2;
        this.ll = ll2;
        this.logIn = logIn2;
        this.logo = logo2;
        this.password = password3;
        this.password2 = password22;
        this.passwordSignup = passwordSignup2;
        this.person = person2;
        this.personName = personName2;
        this.progressSignup = progressSignup2;
        this.relative = relative2;
        this.signup = signup2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySignupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivitySignupBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_signup, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySignupBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.appName;
        TextView appName2 = (TextView) ViewBindings.findChildViewById(view, R.id.appName);
        if (appName2 != null) {
            id = R.id.email_signup;
            EditText emailSignup2 = (EditText) ViewBindings.findChildViewById(view, R.id.email_signup);
            if (emailSignup2 != null) {
                id = R.id.email_view;
                LinearLayout emailView2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.email_view);
                if (emailView2 != null) {
                    id = R.id.imageView;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageView);
                    if (imageView2 != null) {
                        id = R.id.ll;
                        LinearLayout ll2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll);
                        if (ll2 != null) {
                            id = R.id.logIn;
                            TextView logIn2 = (TextView) ViewBindings.findChildViewById(view, R.id.logIn);
                            if (logIn2 != null) {
                                id = R.id.logo;
                                ImageView logo2 = (ImageView) ViewBindings.findChildViewById(view, R.id.logo);
                                if (logo2 != null) {
                                    id = R.id.password;
                                    LinearLayout password3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.password);
                                    if (password3 != null) {
                                        id = R.id.password2;
                                        LinearLayout password22 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.password2);
                                        if (password22 != null) {
                                            id = R.id.password_signup;
                                            EditText passwordSignup2 = (EditText) ViewBindings.findChildViewById(view, R.id.password_signup);
                                            if (passwordSignup2 != null) {
                                                id = R.id.person;
                                                EditText person2 = (EditText) ViewBindings.findChildViewById(view, R.id.person);
                                                if (person2 != null) {
                                                    id = R.id.personName;
                                                    LinearLayout personName2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.personName);
                                                    if (personName2 != null) {
                                                        id = R.id.progress_signup;
                                                        LottieAnimationView progressSignup2 = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.progress_signup);
                                                        if (progressSignup2 != null) {
                                                            id = R.id.relative;
                                                            RelativeLayout relative2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.relative);
                                                            if (relative2 != null) {
                                                                id = R.id.signup;
                                                                AppCompatButton signup2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.signup);
                                                                if (signup2 != null) {
                                                                    return new ActivitySignupBinding((RelativeLayout) view, appName2, emailSignup2, emailView2, imageView2, ll2, logIn2, logo2, password3, password22, passwordSignup2, person2, personName2, progressSignup2, relative2, signup2);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
