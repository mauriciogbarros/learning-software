# The Pragmatic Programmer — Summary

## Chapter 1: A Pragmatic Philosophy

Pragmatic Programmers think beyond the immediate problem, make intelligent compromises, and take ownership of their work.

**Responsibility & Trust**
- Take active responsibility for your career, projects, and day-to-day work.
- Own your mistakes — provide options and contingency plans, not excuses.
- Your team must be able to trust and rely on you.

**Software Entropy**
- Don't live with broken windows (bad designs, wrong decisions, poor code). Fix them or board them up.
- Hopelessness is contagious; one ignored problem invites more.

**Be a Catalyst**
- Start small, show progress, and let others join an ongoing success.
- Always keep the big picture in mind — watch for gradual environmental changes (boiled frog effect).

**Good-Enough Software**
- Involve users in quality trade-offs. Know when to stop adding layers.
- "Good enough" doesn't mean sloppy — it means fit for purpose.

**Knowledge Portfolio**
- Knowledge is an expiring asset; invest in it regularly.
- Diversify: learn new languages, read technical and non-technical books, experiment, stay current.
- Think critically about what you read — follow the money, ask "why?", consider context.

**Communication**
- Know your audience, plan what you want to say, and choose the right moment and style.
- Listen actively. Always respond to people.
- Build documentation in, don't bolt it on. Comment *why*, not *how*.

---

## Chapter 2: A Pragmatic Approach

**Good Design = Easier to Change (ETC)**
- Every design principle (decoupling, naming, DRY) is a special case of ETC.
- Ask: "Did what I just did make the system easier or harder to change?"

**DRY — Don't Repeat Yourself**
- Every piece of knowledge must have a single, unambiguous, authoritative representation.
- DRY applies to code, documentation, data, and APIs — not just copy-pasted code.
- Make it easy to reuse.

**Orthogonality**
- Two things are orthogonal if changes in one don't affect the other.
- Write shy code: self-contained modules with a single, well-defined purpose.
- Avoid global data; prefer the Strategy pattern over similar functions.
- Orthogonality reduces risk and increases productivity.

**Reversibility**
- No decision is final. Design so things can change.
- Hide third-party APIs behind your own abstraction layers.
- Break code into components even if deployed as a monolith.

**Tracer Bullets**
- Find a thin slice of functionality that exercises the full system end-to-end.
- Tracer code is production-quality, not throwaway — it becomes the framework.
- Advantages: early feedback, integration platform, visible progress.
- Different from prototyping: tracer code stays; prototypes are thrown away.

**Prototypes**
- Prototype to learn, not to ship. Ignore correctness, completeness, robustness, and style.
- Make sure everyone knows prototype code is disposable.

**Domain Languages**
- Program close to the problem domain.
- Use off-the-shelf external languages (YAML, JSON, CSV) when appropriate.

**Estimating**
- Build a model, decompose it, assign values to parameters, calculate.
- Iterate the schedule with the code.
- When asked for an estimate: "I'll get back to you."

---

## Chapter 3: The Basic Tools

**Plain Text**
- Store knowledge in plain text — it's insurance against obsolescence, leverages all tools, and is easier to test.
- Plain text includes HTML, JSON, YAML, and most internet protocols.

**Shell**
- The command shell is your workbench. GUI is "what you see is all you get."
- Customize your shell: aliases, prompt, completion, color themes.

**Editor Fluency**
- Achieve fluency so you no longer think about the mechanics of editing.
- Every time you do something repetitive, find a better way.

**Version Control**
- Always use version control — for source, docs, and everything needed to build the project.
- VCS enables branching, collaboration, deployment pipelines, and history.

**Debugging**
- Fix the problem, not the blame. Don't panic.
- Make bugs reproducible. Write a failing test before fixing.
- Read the error message. Use binary chop, rubber ducking, and process of elimination.
- "select" isn't broken — assume the bug is in your code first.

**Text Manipulation**
- Learn a text manipulation language (Python, Ruby, awk, sed).

**Engineering Daybooks**
- Keep a journal: notes, ideas, variable values, reminders. More reliable than memory.

---

## Chapter 4: Pragmatic Paranoia

You can't write perfect software — build defenses against your own mistakes.

**Design by Contract (DBC)**
- Define preconditions, postconditions, and class invariants for every routine.
- Be strict about what you accept; promise as little as possible in return.
- DBC is more efficient than defensive programming and complements (but differs from) TDD.

**Crash Early**
- Dead programs do less damage than crippled ones.
- Crash at the site of the problem for more accurate diagnostics.
- Every case/switch needs a default clause.

**Assertions**
- Use assertions to prevent the impossible. Don't use them in place of real error handling.
- Leave assertions turned on in production unless there's a proven performance issue.

**Resource Balancing**
- The function that allocates a resource should deallocate it.
- Deallocate in the reverse order of allocation to avoid orphaned resources.
- Use `finally` blocks or RAII (scope-based) patterns to guarantee cleanup.

**Take Small Steps**
- Check for feedback and adjust before proceeding.
- Design code to be replaceable rather than trying to predict the future.
- Avoid fortune-telling — black swans exist.

---

## Chapter 5: Bend, or Break

**Decoupling**
- Coupling is the enemy of change. It's also transitive.
- Tell, Don't Ask (TDA): don't make decisions based on an object's internal state.
- Law of Demeter: avoid chaining more than one "." when accessing something.
- Avoid global data; wrap external resources behind an API.
- Inheritance adds coupling — prefer interfaces, delegation, and mixins.

**Events**
Four strategies for event-driven design:
1. **Finite State Machines** — underused; express them as data.
2. **Observer Pattern** — observable + list of observers; can introduce coupling and bottlenecks.
3. **Publish/Subscribe** — decouples publishers and subscribers via named channels; potentially asynchronous.
4. **Reactive Streams** — treat events as collections; unify sync and async processing.

**Transforming Programming**
- Think of programs as pipelines that transform inputs into outputs.
- Data flows through a sequence of `code → data → code → data`.
- Never pass raw values between transformations — wrap them in a type that signals validity.

**Inheritance Tax**
- Inheritance is coupling. Prefer:
  - **Interfaces/Protocols** for polymorphism
  - **Delegation** ("has-a" over "is-a")
  - **Mixins/Traits** for shared functionality

**Configuration**
- Keep environment-specific values external to the app.
- Wrap configuration behind a thin API rather than accessing flat files directly.
- Configuration-as-a-Service enables dynamic, shared, globally-updatable config.

---

## Chapter 6: Concurrency

- **Concurrency**: code acts as if running simultaneously (fibers, threads, processes).
- **Parallelism**: code actually runs simultaneously (multiple cores/machines).
- Shared mutable state is the root cause of most concurrency bugs.

**Breaking Temporal Coupling**
- Analyze workflow with activity diagrams to find what can run concurrently.
- Look for operations that wait on I/O — those are opportunities for concurrency.

**Shared State is Incorrect State**
- Nonatomic updates cause race conditions.
- Use semaphores/mutexes, or better: make resources transactional.
- Random failures are often concurrency issues.

**Actors**
- Actors are independent virtual processors with private state and a mailbox.
- No shared state, no explicit synchronization needed.
- All messages are one-way; actors process one message at a time.

**Blackboards**
- Agents independently read/write to a shared blackboard.
- Useful for coordinating workflow where participants don't need to know about each other.
- Messaging systems like Kafka can serve as blackboards.

---

## Chapter 7: While You Are Coding

**Listen to Your Instincts**
- Instincts are patterns packed into your nonconscious brain from experience.
- If coding feels wrong, stop and prototype to explore the issue.
- Write "I am prototyping" on a sticky note — give yourself permission to explore.

**Program Deliberately**
- Avoid programming by coincidence (relying on undocumented behavior or phantom patterns).
- Always know why your code works. Document assumptions. Test them.
- Don't let existing code dictate future code.

**Algorithm Speed**
- Understand Big-O notation: O(n), O(n²), O(log n), O(n log n).
- Estimate the order of your algorithms; test your estimates with real data.
- Beware premature optimization — confirm a bottleneck before optimizing.

**Refactoring**
- Software is more like gardening than construction — it needs ongoing care.
- Refactor early and often: duplication, non-orthogonal design, outdated knowledge, performance.
- Rules: don't add features while refactoring; have good tests; take small steps.

**Testing**
- Testing is about feedback on design, API, and coupling — not just finding bugs.
- A test is the first user of your code. Hard-to-test code is poorly designed code.
- TDD cycle: decide → write failing test → write minimal code → refactor.
- Build end-to-end, not top-down or bottom-up.
- Test your software, or your users will.

**Property-Based Testing**
- Define contracts and invariants, then let the computer generate test cases.
- Complements unit testing; often reveals unexpected edge cases.

**Security**
- Minimize attack surface area; sanitize all input.
- Principle of least privilege; secure defaults; encrypt sensitive data.
- Apply security patches quickly. Rely only on well-vetted libraries.

**Naming**
- Names define the reality you create. Name things according to their role.
- Rename when needed — make renaming easy and do it often.

---

## Chapter 8: Before the Project

**Requirements**
- No one knows exactly what they want. Your job is to help them figure it out.
- Requirements are learned in a feedback loop — prefer short iterations.
- Walk in your client's shoes; work with users to think like users.
- Policy is metadata — implement the general case, not hardcoded rules.
- Good requirements are abstract; capture semantic invariants, not current work practices.
- Maintain a project glossary for consistent terminology.

**Solving Impossible Puzzles**
- Identify real constraints vs. assumed ones. Challenge preconceived notions.
- Find the most restrictive constraints first, then fit others within them.
- When stuck, step away or explain the problem to someone else.

**Working Together**
- Pair programming and mob programming improve quality and reduce shortcuts.
- Build the code, not your ego. Critique the code, not the person.

**Agility**
- Agile is not a noun — it's how you do things.
- No fixed process survives uncertainty. Respond to what you discover.
- The loop: work out where you are → take the smallest meaningful step → evaluate → repeat.
- Good design makes agility possible.

---

## Chapter 9: Pragmatic Projects

**Pragmatic Teams**
- Small (under 10–12), stable teams where everyone knows and trusts each other.
- Teams must not tolerate broken windows. Quality is everyone's responsibility.
- Schedule time for maintenance, learning, experiments, and process improvement.
- Communicate with one voice; generate a team brand.
- Organize around fully functional end-to-end features, not layers.

**Process**
- Do what works, not what's fashionable. Try things; keep what helps; discard the rest.
- No methodology fits every context — adapt and combine.
- The goal: deliver working software that gives users new capability when they need it.

**Pragmatic Starter Kit**
- Everything needed to build the project lives in version control.
- Builds, tests, and releases are triggered automatically from VCS.
- Test early, test often, test automatically. Coding isn't done until all tests pass.
- Testing layers: unit → integration → validation → performance → property-based.
- Find bugs once — add a regression test for every bug that slips through.
- No manual procedures: people aren't as repeatable as computers.

**Delight Your Users**
- Understand the underlying business expectations, not just stated requirements.
- Every decision should move closer to those expectations.
- Forge a relationship where you actively help solve problems.

**Pride and Prejudice**
- Sign your work. Take pride in what you build.
- Treat other people's code with respect. Anonymity breeds sloppiness.

---

## Personal Reflections

1. Pay attention to details but stay aware of the big picture.
2. Time is your greatest asset — use it wisely and invest in yourself.
3. Don't accept broken windows; be a catalyst for change while knowing what "good enough" means.
4. Knowledge expires — constant investment in your portfolio is required. It's not just what you know, but how you communicate it.
5. Good design adapts to change: avoid repetition, aim for orthogonality, keep things reversible.
6. Use tracer bullets to validate direction, prototypes to answer specific questions, and estimates to avoid surprises.
7. Program close to the problem domain.
8. Plain text is the best format for storing knowledge — it never expires.
9. The shell is your workbench and the editor is your tool — master both.
10. Code is always changing: use version control, fix problems not blame, and master text manipulation for debugging.

**Themes worth exploring further:**
- Programming defensively: accepting imperfection
- Responsibility in code: contracts between components
- The cost of hidden coupling
- Concurrency and reality: why systems are hard
- Avoiding accidental programming
- Refactoring as a habit, not an event
- Testing and trust in software
- Thinking like a craftsperson
