\documentclass{article}
\usepackage{amsmath}
\usepackage{amsthm}
\begin{document}
  \section{Problem 1}
    Data structures describes the ways to store and use data so that you can make efficient programs.\\\\
    Algorithms describes ways to efficiently process data from an input to an output.
  \section{Problem 2}
    \begin{proof}
      \begin{center}
      We want to show that 
        \begin{equation*}
          \exists \: \text{constants} \: c>0,n_0>0 
        \end{equation*}
          \, such\, that \,\,
        \begin{equation*}
          \forall n>n_0, \; \; 0\le 3n^2+n\sqrt{n}\le c*n^2
        \end{equation*}
          so
        \begin{align}
        0\le 3n+n\sqrt{n} &\le c * n^2\\
        \intertext{divide by $n^2$}
        0\le 3+\frac{1}{\sqrt{n}} &\le c\\
        \intertext{then take $\lim\limits_{n \to \infty}$}
        0\le 3 \le c
        \end{align}
        therefore choose $c\ge 3$ and some big $n_0$
      \end{center}
    \end{proof}
    \section{Problem 3}
    \begin{proof}
      \begin{center}
      We want to show that 
        \begin{equation*}
          \forall \: \text{constants} \: c>0,\exists\:\text{constant}\:n_0>0 
        \end{equation*}
          \, such\, that \,\,
        \begin{equation*}
          \forall n>n_0, \; \; 0\le 2(n+100\sqrt{n})(\log{n})^2 \le c* \frac{n\sqrt{n}}{\log{n}}
        \end{equation*}
          so
        \begin{align}
        0\le 2(n+100\sqrt{n})(\log{n})^2 &< c* \frac{n\sqrt{n}}{\log{n}}
        \intertext{multiply by $\log(n)$}
        0\le 2(n+100\sqrt{n})(\log{n})^3 &< c* n\sqrt{n}
        \intertext{distribute}
        0\le 2n(\log{n})^3+200\sqrt{n}(\log{n})^3 &< c* n\sqrt{n}
        \intertext{divide by $n\sqrt{n}$}
        0\le \frac{2(\log{n})^3}{\sqrt{n}}+\frac{200(\log{n})^3}{n} &< c
        \intertext{now we want to get it into a nice form to take the limit}
        0\le (\frac{2^\frac{1}{3}(\log{n})}{n^\frac{1}{6}})^3+(\frac{200^\frac{1}{3}(\log{n})}{n^\frac{1}{3}})^3 &< c
        \intertext{then take $\lim\limits_{n \to \infty}$}
        \lim\limits_{n \to \infty} 0\le \lim\limits_{n \to \infty} (\frac{2^\frac{1}{3}(\log{n})}{n^\frac{1}{6}})^3+(\frac{200^\frac{1}{3}(\log{n})}{n^\frac{1}{3}})^3 &< \lim\limits_{n \to \infty} c
        \intertext{by sum and product laws of limits we can do:}
        0\le (\lim\limits_{n \to \infty}\frac{2^\frac{1}{3}(\log{n})}{n^\frac{1}{6}})^3+(\lim\limits_{n \to \infty}\frac{200^\frac{1}{3}(\log{n})}{n^\frac{1}{3}})^3 &< c
        \intertext{then, using l'hopital's rule ($\lim\limits_{x \to \infty} \frac{f(x)}{h(x)} = \lim\limits_{x \to \infty} \frac{f'(x)}{h'(x)}$)}
        \intertext{\emph{note that $\frac{d}{dx}log_a x=\frac{1}{xln(a)}$}}
        0\le (\lim\limits_{n \to \infty}\frac{2^\frac{1}{3}(\frac{1}{n\ln(10)})}{\frac{1}{6}n^{-\frac{5}{6}}})^3+(\lim\limits_{n \to \infty}\frac{200^\frac{1}{3}\frac{1}{n\ln(10)}}{\frac{1}{3}n^{-\frac{2}{3}}})^3 &< c
        \intertext{distributing:}
        0\le (\lim\limits_{n \to \infty}\frac{2^\frac{1}{3}}{\frac{1}{6}n^\frac{1}{6}\ln(10)})^3+(\lim\limits_{n \to \infty}\frac{200^\frac{1}{3}}{\frac{1}{3}n^\frac{1}{3}\ln(10)})^3 &< c
        \intertext{now just take the limit}
        0\le (\frac{2^\frac{1}{3}}{\infty})^3+(\frac{200^\frac{1}{3}}{\infty})^3 &< c
        \intertext{which goes to zero}
        0\le 0&< c
        \end{align}
        therefore all $c > 0$ work for some big $n_0$
      \end{center}
    \end{proof}
    \section{Problem 4}
    \begin{proof}
      \begin{center}
      We want to show that 
        \begin{equation*}
          \exists \: \text{constants} \: c>0,n_0>0 
        \end{equation*}
          \, such\, that \,\,
        \begin{equation*}
          \forall n>n_0, \; \; 0\le c*n^3\le 10n^3+7n\log{n}
        \end{equation*}
          so
        \begin{align}
        0\le c*n^3&\le 10n^3+7n\log{n}
        \intertext{divide by $n^3$}
        0\le c&\le 10+\frac{7\log{n}}{n^2}
        \intertext{take $\lim\limits_{n \to \infty}$}
        0\le c&\le 10+\lim\limits_{n \to \infty}\frac{7\log{n}}{n^2}
        \intertext{l'hopital}
        0\le c&\le 10+\lim\limits_{n \to \infty}\frac{7\frac{1}{nln(10)}}{2n}
        \intertext{distribute}
        0\le c&\le 10+\lim\limits_{n \to \infty}\frac{7}{2n^2ln(10)}
        \intertext{limit makes the term go to zero}
        0\le c&\le 10
        \end{align}
        therefore choose $0<c\le 10$ and some big $n_0$ in order to make $f(n)\in\Omega(n^3)$
      \end{center}
    \end{proof}
    \section{Problem 5}
    \begin{proof}
      \begin{center}
      We want to show that 
        \begin{equation*}
          \forall \: \text{constants} \: c>0,\exists \:\text{constant}\:n_0>0 
        \end{equation*}
          \, such\, that \,\,
        \begin{equation*}
          \forall n>n_0, \; \; 0\le c* n\log{n}< 2n^2+5n\sqrt{n}
        \end{equation*}
          so
        \begin{align}
        0\le c* n\log{n}&< 2n^2+5n\sqrt{n}
        \intertext{divide by $n\log{n}$}
        0\le c &< \frac{2n}{\log{n}}+\frac{5\sqrt{n}}{\log{n}}
        \intertext{then take $\lim\limits_{n \to \infty}$}
        0\le c &< \lim\limits_{n \to \infty}\frac{2n}{\log{n}}+\lim\limits_{n \to \infty}\frac{5\sqrt{n}}{\log{n}}
        \intertext{l'hopital}
        0\le c &< \lim\limits_{n \to \infty}\frac{2}{\frac{1}{n\ln(10)}}+\lim\limits_{n \to \infty}\frac{\frac{5}{2}n^{-\frac{1}{2}}}{\frac{1}{n\ln(10)}}
        \intertext{distribute}
        0\le c &< \lim\limits_{n \to \infty} 2n\ln(10)+\lim\limits_{n \to \infty} \frac{5}{2}\sqrt{n}\ln(10)
        \intertext{terms go to infinity}
        \end{align}
        therefore all $c>0$ should work with a large enough $n_0$
      \end{center}
    \end{proof}
    \section{Problem 6}
    \begin{proof}
      \begin{center}
      We want to show
        \begin{equation*}
          \exists \: \text{positive constants} \: c_1,c_2,n_0 
        \end{equation*}
          \, such\, that \,\,
        \begin{equation*}
          \forall n>n_0, \; \; 0\le c_1*a(n) \le b(n) \le c_2*a(n)
        \end{equation*}
          where $b(n)$ is the function we want to prove is in $\Theta(a(n))$
          and
          $b(n) = f(n)+g(n) $ for some positive,increasing $f(n)$ and $g(n)$
          $a(n) = \max{f(n),g(n)} $
          so
        \begin{align}
        0\le c_1*a(n) \le &b(n) \le c_2*a(n)
        \intertext{Focusing first on proving the left half}
        c_1*a(n) \le &b(n)
        \intertext{divide by $a(n)$}
        c_1 \le &\frac{b(n)}{a(n)}
        \intertext{substitute}
        c_1 \le &\frac{f(n)+g(n)}{\max{f(n),g(n)}}
        \intertext{distribute}
        c_1 \le &\frac{f(n)}{\max{f(n),g(n)}}+\frac{g(n)}{\max{f(n),g(n)}}
        \intertext{consider cases:}
          \intertext{case 1 : $f(n) > g(n)$}
            \implies c_1 &\frac{1}{1}+\frac{g(n)}{f(n)}
          \intertext{case 2 : $f(n) < g(n)$}
            \implies c_1 &\frac{f(n)}{g(n)}+\frac{1}{1}
          \intertext{case 3 : $f(n) = g(n)$}
            \implies c_1 &\frac{1}{1}+\frac{1}{1}
        \intertext{so}
        1 < &\frac{f(n)}{\max{f(n),g(n)}}+\frac{g(n)}{\max{f(n),g(n)}} \le 2
        \intertext{therefore choose $0<c_1<1$. $n_0$ can be anything positive.}
        \\
        \intertext{Next is proving the right half}
        &b(n) \le c_2*a(n)
        \intertext{divide by $a(n)$}
        &\frac{b(n)}{a(n)}&b(n) \le c_2
        \intertext{substitute}
        &\frac{f(n)+g(n)}{\max{f(n),g(n)}} \le c_2
        \intertext{distribute}
        &\frac{f(n)}{\max{f(n),g(n)}}+\frac{g(n)}{\max{f(n),g(n)}} \le c_2
        \intertext{We've already shown that:}
          1 < &\frac{f(n)}{\max{f(n),g(n)}}+\frac{g(n)}{\max{f(n),g(n)}} \le 2
        \end{align}
        Therefore choose $c_2 \ge 2$. $n_0$ can be anything positive.\\
        In conclusion, $b(n) \in \Theta(a(n))$
      \end{center}
    \end{proof}
    \section{Problem 7}
    \begin{proof}
      \begin{center}
      No, counterexample:\\
        choose $f(n) = \ln(n)$\\
        then $f(\sqrt{n}) = \ln(\sqrt{n})$\\
        now if we try to prove like normal we need:
        \begin{equation*}
          \forall \: \text{constants} \: c>0,\exists \:\text{constant}\:n_0>0 
        \end{equation*}
          \, such\, that \,\,
        \begin{equation*}
          \forall n>n_0, \; \; 0 \le c*f(\sqrt{n}) < f(n)
        \end{equation*}
          so
        \begin{align}
        0 \le c*f(\sqrt{n}) &< f(n)
        \intertext{substitute}
        0 \le c*\ln(\sqrt{n}) &< \ln(n)
        \intertext{log rules}
        0 \le \frac{c}{2}*\ln(n) &< \ln(n)
        \end{align}
        therefore all $c \ge 2$ don't work
      \end{center}
    \end{proof}
\end{document}
